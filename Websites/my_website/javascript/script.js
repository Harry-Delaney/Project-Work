// Calling showTime function at every second
setInterval(showTime, 1000);
 
// Defining showTime funcion
function showTime() {
    // Getting current time and date
    let time = new Date();
    let hour = time.getHours();
    let min = time.getMinutes();
    let sec = time.getSeconds();
    am_pm = "AM";
 
    // Setting time for 12 Hrs format
    if (hour >= 12) {
        if (hour > 12) hour -= 12;
        am_pm = "PM";
    } else if (hour == 0) {
        hr = 12;
        am_pm = "AM";
    }
 
    hour =
        hour < 10 ? "0" + hour : hour;
    min = min < 10 ? "0" + min : min;
    sec = sec < 10 ? "0" + sec : sec;
 
    let currentTime =
        hour +
        ":" +
        min +
        ":" +
        sec +
        am_pm;
 
    // Displaying the time
    document.getElementById(
        "clock"
    ).innerHTML = currentTime;
}
 
showTime();

function darkMode() {
    var element = document.body;
    element.className = "dark-mode";
    content.innerText = "Dark Mode is ON";
    alert("You are now in dark mode");

}
function lightMode() {
    var element = document.body;
    element.className = "light-mode";   
    content.innerText = "Dark Mode is OFF"; 
    alert("You are in light mode");
}

const name = ["1.Arsenal", "2.Man City", "3.Liverpool", "4.Aston Villa", "5.Tottenham"];
document.getElementById("clientName").innerHTML = name[0];
document.getElementById("clientName1").innerHTML = name[1];
document.getElementById("clientName2").innerHTML = name[2];
document.getElementById("clientName3").innerHTML = name[3];
document.getElementById("clientName4").innerHTML = name[4];

// Array of Irish counties
const irishCounties = [
    'Arsenal', 'Aston Villa', 'Bounemouth', 'Brentford', 'Brighton & Hove Albion', 'Burnley', 'Chelsea',
    'Crystal Palace', 'Everton', 'Fulham', 'Liverpool', 'Luton Town', 'Manchester City', 'Manchester United',
    'Newcastle United', 'Nottingham Forest', 'Sheffield United', 'Tottenham Hotspur', 'West Ham United', 'Wolverhampton Wanderers'
  ];
  
  // Function to display Irish counties on the webpage
  function displayCounties() {
    const countyList = document.getElementById('county-list');
  
    irishCounties.forEach(county => {
      // Create a new list item element
      const listItem = document.createElement('li');
      
      // Set the text content of the list item
      listItem.textContent = county;
  
      // Append the list item to the unordered list
      countyList.appendChild(listItem);
    });
  }
  
  // Call the function to display Irish counties
  displayCounties();
    