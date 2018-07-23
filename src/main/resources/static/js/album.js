const btnSubmitComment = document.getElementById('submitButton');
const albumCommentsUl = document.querySelector('.albumComments');
const songsUl = document.querySelector('.songList');

const hrefArray = document.URL.split('/');
const albumName = hrefArray[6];

function renderComments(response) {
  if (this.status === 200 && this.readyState === 4) {
    const remainingComments = JSON.parse(response.currentTarget.response);
    let commentHTML = '';
    remainingComments.forEach((comment) => {
      commentHTML += `
      <li>
        ${comment.description}
      </li>
      `;
    });
    albumCommentsUl.innerHTML = commentHTML;
  }
}

function renderAlbumSongs(response) {
  if (this.status === 200 && this.readyState === 4) {
    const remainingSongs = JSON.parse(response.currentTarget.response);
    let songsHTML = '';
    remainingSongs.forEach((song) => {
      songsHTML += `
      <li>
        <a href="/album/${albumName}/${song.songName}">
          ${song.songName}
        </a>
      </li>
      `;
    });
    songsUl.innerHTML = songsHTML;
  }
}

function addComment() {
  const xhr = new XMLHttpRequest();
  const commentDescription = document.querySelector('[name="commentDescription"]');
  xhr.open('POST', `/api/album/${albumName}/post-comment?commentDescription=${commentDescription.value}`, true);
  xhr.addEventListener('readystatechange', renderComments);
  xhr.send();
}

function showAlbumComments() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/album/${albumName}/comments`, true);
  xhr.addEventListener('readystatechange', renderComments);
  xhr.send();
}

function showAlbumSongs() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `/api/album/${albumName}/songs`, true);
  xhr.addEventListener('readystatechange', renderAlbumSongs);
  xhr.send();
}

showAlbumSongs();
showAlbumComments();
btnSubmitComment.addEventListener('click', addComment);