function searchGame() {
    const gameName = document.getElementById('gameName').value.trim();

    if (!gameName) {
        alert("Please enter a game name.");
        return;
    }

    // Call the backend endpoint to search for games by name
    fetch(`/games/name/${encodeURIComponent(gameName)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('No games found');
            }
            return response.json(); // Parse response as JSON
        })
        .then(data => {
            const gameTable = document.getElementById('gameTable');
            const gameInfo = document.getElementById('gameInfo');
            const errorMessage = document.getElementById('errorMessage');

            // Check if the response is an empty array
            if (Array.isArray(data) && data.length > 0) {
                // Clear previous data
                gameInfo.innerHTML = "";

                // Add each game to the table
                data.forEach(game => {
                    let row = `<tr><td>${game.id}</td><td>${game.name}</td></tr>`;
                    gameInfo.innerHTML += row;
                });

                // Show table and hide error message
                gameTable.style.display = "table";
                errorMessage.style.display = "none";
            } else {
                // Hide table and show error message
                gameTable.style.display = "none";
                errorMessage.style.display = "block";
            }
        })
        .catch(error => {
            console.error("Fetch Error:", error);
            // Hide table and show error message
            document.getElementById('gameTable').style.display = "none";
            document.getElementById('errorMessage').style.display = "block";
        });
}