// src/services/ProductService.js
import axios from 'axios';

const API_URL = 'http://localhost:9999/api/public';

const ProductService = {
  search: async (keyword) => {
    const response = await axios.get(`${API_URL}/search`, {
      params: { keyword }
    });
    return response.data;
  }
};

export default ProductService;
