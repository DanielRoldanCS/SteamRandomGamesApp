function searchGame() {
    const gameIndex = document.getElementById('gameIndex').value.trim();

    if (!gameIndex) {
        alert("Please enter a game index.");
        return;
    }

    // Call the backend endpoint to search for the game by index
    fetch(`/games/index/${gameIndex}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Index is out of bounds');
            }
            return response.text(); // Parse response as text
        })
        .then(data => {
            const gameTable = document.getElementById('gameTable');
            const gameInfo = document.getElementById('gameInfo');
            const errorMessage = document.getElementById('errorMessage');

            // Check if the response contains "Index specified is out of bounds"
            if (data === "Index specified is out of bounds") {
                // Hide table and show error message
                gameTable.style.display = "none";
                errorMessage.style.display = "block";
            } else {
                // Extract the ID and name from the response
                const [idPart, namePart] = data.split(" Name:");
                const id = idPart.replace("ID:", "").trim();
                const name = namePart.trim();

                // Display the game information
                gameInfo.innerHTML = `<tr><td>${id}</td><td>${name}</td></tr>`;
                gameTable.style.display = "table"; // Show table
                errorMessage.style.display = "none"; // Hide error message
            }
        })
        .catch(error => {
            console.error("Fetch Error:", error);
            // Hide table and show error message
            document.getElementById('gameTable').style.display = "none";
            document.getElementById('errorMessage').style.display = "block";
        });
}