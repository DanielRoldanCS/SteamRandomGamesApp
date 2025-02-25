// Store all games from the API
let allGames = [];
// Keep track of the current page
let currentPage = 1;
// Number of games to show per page
const gamesPerPage = 20;

// Function to fetch games from the API
function updateGames() {
    fetch('games')
        .then(response => {
            // Check if the response is OK
            if (!response.ok) {
                throw new Error('Something went wrong with the request.');
            }
            // Convert the response to JSON
            return response.json();
        })
        .then(data => {
            // Check if the data is an array and not empty
            if (Array.isArray(data) && data.length > 0) {
                // Save the games to the allGames variable
                allGames = data;
                // Reset to the first page
                currentPage = 1;
                // Show a success message
                document.getElementById('updateResponse').innerText = "Games updated successfully!";
                // Display the games
                displayGames();
                // Update the pagination buttons
                updatePagination();
            } else {
                // Show a message if no games are found
                document.getElementById('updateResponse').innerText = "No games found.";
                console.warn("No games in the response.");
            }
        })
        .catch(error => {
            // Show an error message if something goes wrong
            document.getElementById('updateResponse').innerText = "Error updating games.";
            console.error("Error:", error);
        });
}

// Function to display games on the page
function displayGames() {
    // Get the table and the list where games will be shown
    let gameTable = document.getElementById('gameTable');
    let gameList = document.getElementById('gameList');

    // Clear the list before adding new games
    gameList.innerHTML = "";

    // Calculate which games to show for the current page
    const startIndex = (currentPage - 1) * gamesPerPage;
    const endIndex = startIndex + gamesPerPage;
    const gamesToDisplay = allGames.slice(startIndex, endIndex);

    // Loop through the games and add them to the table
    gamesToDisplay.forEach(game => {
        // Create a new row for each game
        let row = document.createElement('tr');
        // Add the game's ID and name to the row
        row.innerHTML = `<td>${game.appid}</td><td>${game.name}</td>`;
        // Add the row to the list
        gameList.appendChild(row);
    });

    // Show the table and the pagination if it was hidden
    gameTable.style.display = "table";
    document.getElementById("pagination").style.display = "initial";

}

// Function to update the pagination buttons and page info
function updatePagination() {
    // Calculate the total number of pages
    const totalPages = Math.ceil(allGames.length / gamesPerPage);
    // Update the page info text
    document.getElementById('pageInfo').innerText = `Page ${currentPage} of ${totalPages}`;

    if(currentPage==1){
        document.getElementById("prevPage").disabled = true;
    }
    else{
        document.getElementById("prevPage").disabled = false;
    }

    if(currentPage == totalPages){
        document.getElementById("nextPage").disabled = true;
    }
    else {
        document.getElementById("nextPage").disabled = false;
    }
}

// Function to go to the previous page
function previousPage() {
    if (currentPage > 1) {
        // Move to the previous page
        currentPage--;
        // Update the games and pagination
        displayGames();
        updatePagination();
    }
}

// Function to go to the next page
function nextPage() {
    // Calculate the total number of pages
    const totalPages = Math.ceil(allGames.length / gamesPerPage);
    if (currentPage < totalPages) {
        // Move to the next page
        currentPage++;
        // Update the games and pagination
        displayGames();
        updatePagination();
    }
}