const btnSubmitBand = document.getElementById('submitAlbum');

const hrefArray = document.URL.split('/');
const bandName = hrefArray[4];

function renderBandAlbums(response) {
  // Sorry Donny, triple-equals is best equals
  if (this.status === 200 && this.readyState === 4) {
    const albumsUl = document.querySelector('.albumsList');
    const remainingBandAlbums = JSON.parse(response.currentTarget.response);
    let listOfBandAlbums = '';
    remainingBandAlbums.forEach((album) => {
      listOfBandAlbums += `
        <li>
            <a href="/band/${bandName}/album/${album.albumName}">
                ${album.albumName}
            </a>
        </li>`;
    });
    albumsUl.innerHTML = listOfBandAlbums;
  }
}

function addAlbum() {
  const xhr = new XMLHttpRequest();
  const albumName = document.querySelector('[name="albumName"]');
  const albumGenre = document.querySelector('[name="albumGenre"]');
  const albumReleaseDate = document.querySelector('[name="albumReleaseDate"]');
  const recordLabel = document.querySelector('[name="recordLabel"]');

  xhr.open('POST',
    `/api/album/add-album?bandName=${bandName}
    &albumName=${albumName.value}
    &albumGenre=${albumGenre.value}
    &albumReleaseDate=${albumReleaseDate}
    &recordLabel=${recordLabel.value}`, true);
  xhr.addEventListener('readystatechange', renderBandAlbums);
  xhr.send();
}

function showBandAlbums() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/band/${bandName}/albums`, true);
  xhr.addEventListener('readystatechange', renderBandAlbums);
  xhr.send();
}

showBandAlbums();
btnSubmitBand.addEventListener('click', addAlbum);