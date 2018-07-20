const btnSubmitArtist = document.getElementById('submitButton');
const artistUl = document.querySelector('.artistList');

showArtists();

function renderArtists(response) {
    if (this.status == 200 && this.readyState == 4) {
        const remainingArtists = JSON.parse(response.currentTarget.response)
        let listOfArtists = '';
        remainingArtists.forEach(artist => {
            listOfArtists += `
            <li>
                <a href="/artists/${artist.artistName}">
                    ${artist.artistName}
                </a>
            </li>
            `
        }); 
        artistUl.innerHTML = listOfArtists;
    }
}

function addArtist() {
    const xhr = new XMLHttpRequest();
    const artistName = document.querySelector(`[name='artistName']`);
    const recordLabel = document.querySelector(`[name='recordLabel']`);
    xhr.open('POST', `/api/artists?artistName=${artistName.value}&recordLabel=${recordLabel.value}`, true);
    xhr.addEventListener('readystatechange', renderArtists)
    xhr.send();
}

function showArtists() {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `/api/artists`, true);
    xhr.addEventListener('readystatechange', renderArtists);
    xhr.send();
}

btnSubmitArtist.addEventListener('click', addArtist);