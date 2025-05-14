import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const Home = () => {
  const [products, setProducts] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [searchTerm, setSearchTerm] = useState('');
  const bannerImages = [
    '/Phone.jpeg',  // Ensure image exists here
    '/Ac.jpeg',     // Ensure image exists here
    '/Laptop.jpeg', // Ensure image exists here
  ];

  useEffect(() => {
    fetchProducts(); // Load products when component mounts
  }, []);

  const fetchProducts = async (query = '') => {
    try {
      // Check if the query is a number (i.e., ID)
      if (!isNaN(query) && query.trim() !== '') {
        // Fetch by product ID if it's a number
        const response = await axios.get(`/api/public/product/${query}`);
        setProducts([response.data]); // Only one product is returned
      } else {
        // Fetch by keyword for name or category search
        const response = await axios.get(`/api/public/searchby?keyword=${query}`);
        setProducts(response.data); // Multiple products are returned
      }
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  // Auto-slide every 7 seconds for the banner
  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIndex((prevIndex) =>
        prevIndex === bannerImages.length - 1 ? 0 : prevIndex + 1
      );
    }, 7000);
    return () => clearInterval(interval);
  }, [bannerImages.length]);

  const handleSearch = () => {
    console.log('Search triggered');
    fetchProducts(searchTerm); // Trigger the fetch based on search term
  };

  return (
    <div style={{ fontFamily: 'Arial, sans-serif' }}>
      {/* Top Navigation Bar */}
      <div style={styles.navbar}>
        <div style={styles.logo}>MyStore</div>
        <div style={styles.searchContainer}>
          <input
            type="text"
            placeholder="Search products..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            style={styles.searchBox}
          />
          <button onClick={handleSearch} style={styles.searchButton}>
            Search
          </button>
        </div>
        <Link to="/login" style={styles.loginLink}>Login</Link>
      </div>

      {/* Banner Carousel */}
      <div style={styles.carousel}>
        <img
          src={bannerImages[currentIndex]}
          alt={`Banner ${currentIndex}`}
          style={styles.bannerImage}
          onError={(e) => {
            e.target.src = "https://via.placeholder.com/1200x300?text=Banner"; // Fallback image
          }}
        />
      </div>

      {/* Products */}
      <div style={styles.productsContainer}>
        {products.length === 0 ? (
          <p>No products found. Please try a different search.</p>
        ) : (
          products.map((product) => (
            <div key={product.id} style={styles.productCard}>
              <img
                src={product.imageUrl}
                alt={product.name}
                style={styles.productImage}
                onError={(e) => {
                  e.target.src = "https://via.placeholder.com/200x200?text=Product"; // Fallback image
                }}
              />
              <h3>{product.name}</h3>
              <p>Price: ₹{product.price}</p>
              <Link to={`/product/${product.id}`} style={styles.detailsButton}>
                View Details
              </Link>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

const styles = {
  navbar: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    padding: '10px 20px',
    backgroundColor: '#2874f0',
    color: 'white',
  },
  logo: {
    fontSize: '24px',
    fontWeight: 'bold',
  },
  searchContainer: {
    display: 'flex',
    alignItems: 'center',
  },
  searchBox: {
    padding: '8px',
    width: '250px',
    borderRadius: '4px',
    border: 'none',
    marginRight: '10px',
  },
  searchButton: {
    padding: '8px 12px',
    backgroundColor: '#ff8c00',
    color: 'white',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
  },
  loginLink: {
    color: 'white',
    textDecoration: 'none',
    fontWeight: 'bold',
  },
  carousel: {
    width: '100%',
    overflow: 'hidden',
    height: '300px',
    marginTop: '10px',
  },
  bannerImage: {
    width: '100%',
    height: '100%',
    objectFit: 'cover',
  },
  productsContainer: {
    display: 'grid',
    gridTemplateColumns: 'repeat(auto-fill, minmax(220px, 1fr))',
    gap: '20px',
    padding: '20px',
  },
  productCard: {
    backgroundColor: '#fff',
    borderRadius: '8px',
    padding: '15px',
    boxShadow: '0 2px 8px rgba(0,0,0,0.1)',
    textAlign: 'center',
  },
  productImage: {
    width: '100%',
    height: '200px',
    objectFit: 'contain',
    marginBottom: '10px',
  },
  detailsButton: {
    display: 'inline-block',
    marginTop: '10px',
    padding: '8px 12px',
    backgroundColor: '#2874f0',
    color: 'white',
    borderRadius: '4px',
    textDecoration: 'none',
  },
};

export default Home;
