function renderArtists(response) {
  if (this.status === 200 && this.readyState === 4) {
    const artistsUl = document.querySelector('.artistsList');
    const remainingArtists = JSON.parse(response.currentTarget.response);
    let listArtists = '';
    remainingArtists.forEach((artist) => {
      listArtists += `
        <li>
            <a href="/artist/${artist.artistName}">
                ${artist.artistName}
            </a>
        </li>`;
    });
    artistsUl.innerHTML = listArtists;
  }
}

function addArtist() {
  const xhr = new XMLHttpRequest();
  const artistName = document.querySelector('[name="artistName"]').value.trim();
  let bandName = '';
  if (document.URL.includes('artists')) {
    bandName = document.getElementById('bandNameInput').value.trim();
    if (bandName.length < 2) {
      //
    }
    xhr.open('POST',
      `/api/artists/add-artist?artistName=${artistName}
        &bandName=${bandName}`, true);
  } else {
    const bandToAdd = document.URL.split('/')[4];
    xhr.open('POST',
      `/api/artists/add-artist?artistName=${artistName}
      &bandName=${bandToAdd}`, true);
  }
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

function renderAddArtistsFieldset() {
  if (this.status === 200 && this.readyState === 4) {
    document.querySelector('.addArtist').innerHTML = this.response;
    const btnSubmitArtist = document.getElementById('submitButton');
    btnSubmitArtist.addEventListener('click', addArtist);
  }
}

function getAddArtistsFieldset() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '/html/addArtist.html', true);
  xhr.addEventListener('readystatechange', renderAddArtistsFieldset);
  xhr.send();
}

function showArtistsInBand(bandName) {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/band/${bandName}/artists`, true);
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

function showArtistsOnAlbum(albumName) {
  console.log(albumName);
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/album/${albumName}/artists`, true);
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

function showArtists() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/artists', true);
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

getAddArtistsFieldset();

if (document.URL.includes('band')) {
  if (document.URL.includes('album')) { 
    showArtistsOnAlbum(document.URL.split('/')[6]);
  } else {
    showArtistsInBand(document.URL.split('/')[4]);
  }
} else {
  showArtists();
}