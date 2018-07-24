const [ , , , , artistBandName, , artistAlbumName] = document.URL.split('/');


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
            <button class="editArtist"> EDIT </button>
            <button class="deleteArtist"> DELETE </button>
        </li>`;
    });
    artistsUl.innerHTML = listArtists;
  }
}

function addArtist(event) {
  const xhr = new XMLHttpRequest();
  const artistName = document.querySelector('[name="artistName"]').value.trim();
  const bandNameInput = document.getElementById('bandNameInput').value.trim();
  let urlHit = '';
  console.log(event.target);
  if (event.target.classList.contains('btnAddArtist')) {
    // if at .../artists
    if (document.URL.includes('artists')) {
      urlHit = document.getElementById('bandNameInput').value;
      console.log(urlHit);
      // if at /band/.../album
    } else if (document.URL.includes('album')) {
      urlHit = `${artistAlbumName}`;
      console.log(`Hit add artist to album on album page API: ${artistAlbumName}`);
      // if at .../band
    } else {
      urlHit = `${artistBandName}`;
      console.log('Hit add artist to band API');
      console.log(artistBandName);
    }
    xhr.open('POST', `/api/artists/add-artist?artistName=${artistName}&urlHit=${urlHit}`, true);
  }
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

function showArtistsInBand() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/band/${artistBandName}/artists`, true);
  console.log('Band: ' + artistBandName);  
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

function showArtistsOnAlbum() {
  console.log('Album: ' + artistAlbumName);
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/album/${artistAlbumName}/artists`, true);
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

function showArtists() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/artists', true);
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}
// hit specific show artists endpoint based on URL accessed
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

function deleteArtist(event) {
  if (event.target.classList.contains('deleteArtist')) {
    let urlHit = '';
    if (document.URL.includes('artists')) {
      urlHit = 'artists';
      // if at .../band
    } else if (document.URL.includes('album')) {
      urlHit = `${artistAlbumName}`;
      console.log('Hit add artist to album on album page API');
    } else {
      // if at /band/.../album
      urlHit = `${artistBandName}`;
      console.log('Hit add artist to band API');
      console.log(artistBandName);
    }

    const deleteButton = event.target;
    const artistContainer = deleteButton.parentElement;
    const artistName = artistContainer.querySelector('a').getAttribute('href').split('/')[2];
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', `/api/artists/delete-artist?artistName=${artistName}&urlHit=${urlHit}`, true);
    xhr.addEventListener('readystatechange', renderArtists);
    xhr.send();
  }
}

const artistsUl = document.querySelector('.artistsList');
artistsUl.addEventListener('click', deleteArtist);
const addArtistButton = document.querySelector('.btnAddArtist');
addArtistButton.addEventListener('click', addArtist);