console.log("This is js")

const toggleSidebar = () => {
   if ($(".sidebar").is(":visible")) {
      // true
      // closed the crossbar

      $(".sidebar").css("display", "none")
      $(".content").css("margin-left", "0%")

         
   } else {
      // false
      // open the crossbar
      $(".sidebar").css("display", "block")
      $(".content").css("margin-left", "20%")
   }
};