function fetchRandomGame() {
    fetch('/random')
        .then(response => response.json())
        .then(data => {
            document.getElementById('gameDisplay').innerText = `ID: ${data.id} | Name: ${data.name}`;
        })
        .catch(error => console.error('Error fetching random game:', error));
}