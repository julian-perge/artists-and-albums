function renderArtists(response) {
  console.log(response);
  
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
  const artistName = document.querySelector('[name="artistName"]').value;
  let bandName = '';
  // if at .../artists
  if (document.URL.includes('artists')) {
    bandName = document.getElementById('bandNameInput').value;
    if (bandName === '') {
      console.log('Band name is null');

    } else if (bandName.length < 2) {
      console.log('Band name is less than 2 characters.');

    }
    xhr.open('POST',
      `/api/artists/add-artist?artistName=${artistName}
        &bandName=${bandName}`, true);
    // if at .../band
  } else {
    // if at /band/.../album
    bandName = document.URL.split('/')[4];
    if (document.URL.includes('album')) {
      document.getElementById('bandNameInput').value = bandName;
    }
    xhr.open('POST',
      `/api/artists/add-artist?artistName=${artistName}
      &bandName=${bandName}`, true);
  }
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}



function showArtistsInBand(bandName) {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/band/${bandName}/artists`, true);
  console.log('Band: ' + bandName);
  
  const bandNameInput = document.getElementById('bandNameInput');
  // bandNameInput.classList.add('hide');
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

function showArtistsOnAlbum(albumName) {
  console.log('Album: ' + albumName);
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

const btnSubmitArtist = document.getElementById('submitButton');
btnSubmitArtist.addEventListener('click', addArtist);

if (document.URL.includes('band')) {
  if (document.URL.includes('album')) {
    console.log('Hit artists for album API');
    showArtistsOnAlbum(document.URL.split('/')[6]);
  } else {
    console.log('Hit artists for band API');
    showArtistsInBand(document.URL.split('/')[4]);
  }
} else {
  showArtists();
}