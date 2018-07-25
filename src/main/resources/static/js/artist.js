const modalBox = document.getElementById('modal-box');
const [, , , , artistBandName, , artistAlbumName] = document.URL.split('/');

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
                <span id="artistId" hidden="true">${artist.id}</span>
            </a>
            <button class="editArtist"> EDIT </button>
            <button class="deleteArtist"> DELETE </button>
        </li>`;
    });
    artistsUl.innerHTML = listArtists;
  }
}

function deleteArtist(event) {
  if (event.target.classList.contains('deleteArtist')) {
    const deleteButton = event.target;
    const artistContainer = deleteButton.parentElement;
    const artistName = artistContainer.querySelector('a').getAttribute('href').split('/')[2];
    let urlHit = '';
    if (document.URL.includes('artists')) {
      urlHit = `${artistName}`;
      // if at .../band
    } else if (document.URL.includes('album')) {
      urlHit = `${artistAlbumName}`;
      // console.log('Hit add artist to album on album page API');
    } else {
      // if at /band/.../album
      urlHit = `${artistBandName}`;
      // console.log('Hit add artist to band API');
    }
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', `/api/artists/delete-artist?artistName=${artistName}&urlHit=${urlHit}`, true);
    xhr.addEventListener('readystatechange', renderArtists);
    xhr.send();
  }
}

function addArtist(event) {
  const xhr = new XMLHttpRequest();
  const artistName = document.querySelector('[name="artistName"]').value.trim();
  const bandNameInput = document.getElementById('bandNameInput').value.trim();
  let urlHit = '';
  if (event.target.classList.contains('btnAddArtist')) {
    // if at .../artists
    if (document.URL.includes('artists')) {
      urlHit = bandNameInput;
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

function submitEditedArtist() {
  const xhr = new XMLHttpRequest();
  const newArtistName = modalBox.querySelector('input[name="artistName"]');
  const artistId = modalBox.querySelector('input[name="artistId"]');
  
  xhr.open('PUT', `/api/artists/edit-artist?newArtistName=${newArtistName.value}&artistId=${artistId.value}`, true);
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

function editArtist(event) {
  if (event.target.classList.contains('editArtist')) {
    modalBox.classList.toggle('hide');
    const xhr = new XMLHttpRequest();
    const artistContainer = event.target.parentElement;
    const artistNameInput = modalBox.querySelector('input[name="artistName"]');
    const artistName = artistContainer.querySelector('a').getAttribute('href').split('/')[2];
    const artistIdInput = modalBox.querySelector('input[name="artistId"]');
    const artistId = artistContainer.querySelector('#artistId');
    // console.log(artistId);
    
    // const bandName = modalBox.querySelector('input[name="bandName"]').value;
    artistNameInput.value = artistName;
    artistIdInput.value = artistId.textContent;
    // xhr.open('GET', `/api/artists?artistName=${artistName}`, true);
    // xhr.addEventListener('readystatechange', function(response) {
    //   console.log(response.target.response);
    // })
    // xhr.send();
  }
}

const btnSubmitEditArtist = document.getElementById('modal-submit');
btnSubmitEditArtist.addEventListener('click', submitEditedArtist);

function showArtistsInBand() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/band/${artistBandName}/artists`, true);
  xhr.addEventListener('readystatechange', renderArtists);
  xhr.send();
}

function showArtistsOnAlbum() {
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
if (document.URL.includes('album')) {
  console.log('Hit artists for album API');
  showArtistsOnAlbum();
} else if (document.URL.includes('band')) {
  console.log('Hit artists for band API');
  showArtistsInBand();
} else {
  showArtists();
}

const artistsUl = document.querySelector('.artistsList');
artistsUl.addEventListener('click', deleteArtist);
artistsUl.addEventListener('click', editArtist);
const addArtistButton = document.querySelector('.btnAddArtist');
addArtistButton.addEventListener('click', addArtist);