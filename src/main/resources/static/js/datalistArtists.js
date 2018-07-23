function listArtists(response) {
  if (this.status === 200 && this.readyState === 4) {
    const datalistArtists = document.getElementById('listArtists');
    const artistsTolist = JSON.parse(response.currentTarget.response);
    let optionList = '';
    artistsTolist.forEach((artist) => {
      optionList += `
        <option value="${artist.artistName}" />
      `;
    });
    datalistArtists.innerHTML = optionList;
  }
}

function showArtistsInInput() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/artists', true);
  xhr.addEventListener('readystatechange', listArtists);
  xhr.send();
}

showArtistsInInput();