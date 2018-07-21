const btnSubmitComment = document.getElementById('submitButton');
const artistAlbumsUl = document.querySelector('.artistAlbums');

// grab url from current document
// i.e. .../artists/artistName/album
const hrefArray = document.URL.split("/");
const artistName = hrefArray[4];

showAlbums();

function renderAlbums(response) {
    if (this.status == 200 && this.readyState == 4) {
        const remainingComments = JSON.parse(response.currentTarget.response);
        let commentHTML = '';
        remainingComments.forEach(comment => {
            commentHTML +=
                `
                <li>
                    ${comment.description}
                </li>
                `
        });
        songCommentsUl.innerHTML = commentHTML;
    }
}

function addAlbum(event) {
    const albumName = document.querySelector(`[name='albumName']`);
    const albumGenre = document.querySelector(`[name='albumGenre']`);
    const albumReleaseDate = document.querySelector(`[name='albumReleaseDate']`);
    const albumCoverImage = document.querySelector(`[name='albumCoverImage']`);
    
    const xhr = new XMLHttpRequest();
    xhr.open('POST', `/api/artists/${artistName}/add-album
                        ?albumName=${albumName.value}
                        &albumGenre=${albumGenre.value}
                        &albumReleaseDate=${albumReleaseDate.value}
                        &albumCoverImage=${albumCoverImage.value}`,
                        true);
    xhr.addEventListener('readystatechange', renderAlbums);
    xhr.send();
}

function showAlbums() {
    // instantiate request to server
    const xhr = new XMLHttpRequest();
    // open GET request to server, with request parameter of the artistName and albumName
    // from the href array
    xhr.open('GET', `/api/artists/${artistName}`, true)
    // xhr.open('POST', `/api/post-comment`)
    xhr.addEventListener('readystatechange', renderAlbums)
    xhr.send();
}

btnSubmitComment.addEventListener('click', addAlbum);