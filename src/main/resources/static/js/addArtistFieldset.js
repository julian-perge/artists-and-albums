function renderAddArtistsFieldset() {
  if (this.status === 200 && this.readyState === 4) {
    document.querySelector('.addArtist').innerHTML = this.response;
  }
}

function getAddArtistsFieldset() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '/html/addArtist.html', true);
  xhr.addEventListener('readystatechange', renderAddArtistsFieldset);
  xhr.send();
}

getAddArtistsFieldset();