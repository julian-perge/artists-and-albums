const btnAddAlbum = document.querySelector('.btnAddAlbum');

const hrefArray = document.URL.split('/');
const bandName = hrefArray[4];

function renderBandAlbums(response) {
  
  // Sorry Donny, triple-equals is best equals
  if (this.status === 200 && this.readyState === 4) {
    const albumsUl = document.querySelector('.albumsList');
    const remainingBandAlbums = JSON.parse(response.currentTarget.response);
    console.log(response.currentTarget.response);
    let listOfBandAlbums = '';
    remainingBandAlbums.forEach((album) => {
      listOfBandAlbums += `
        <li>
            <a href="/band/${bandName}/album/${album.albumName}">
                ${album.albumName}
            </a>
            <button class="editAlbum"> EDIT </button>
            <button class="deleteAlbum"> DELETE </button>
        </li>`;
    });
    albumsUl.innerHTML = listOfBandAlbums;
  }
}

function addAlbum() {
  const xhr = new XMLHttpRequest();
  const albumName = document.querySelector('[name="albumName"]').value;
  const albumGenre = document.querySelector('[name="albumGenre"]').value;
  const albumReleaseDate = document.querySelector('[name="albumReleaseDate"]').value;
  const recordLabel = document.querySelector('[name="albumRecordLabel"]').value;
  xhr.open('POST', `/api/album/add-album?bandName=${bandName}&albumName=${albumName}&albumGenre=${albumGenre}&albumReleaseDate=${albumReleaseDate}&albumRecordLabel=${recordLabel}`, true);
  xhr.addEventListener('readystatechange', renderBandAlbums);
  xhr.send();
}

function deleteAlbum(event) {
  if (event.target.classList.contains('deleteAlbum')) {
    const deleteButton = event.target;
    const albumContainer = deleteButton.parentElement;
    const albumName = albumContainer.querySelector('a').getAttribute('href').split('/')[4];
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', `/api/album/delete-album?albumName=${albumName}&bandName=${bandName}`, true);
    xhr.addEventListener('readystatechange', renderBandAlbums);
    xhr.send();
  }
}

function showBandAlbums() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/band/${bandName}/albums`, true);
  xhr.addEventListener('readystatechange', renderBandAlbums);
  xhr.send();
}

showBandAlbums();
btnAddAlbum.addEventListener('click', addAlbum);
const albumUl = document.querySelector('.albumsList');
albumUl.addEventListener('click', deleteAlbum);