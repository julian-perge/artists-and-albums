const btnSubmitComment = document.getElementById('submitButton');
const songCommentsUl = document.querySelector('.songComments');

// grab url from current document
// i.e. localhost.../artists/artistName/album
const hrefArray = document.URL.split("/");
const artistName = hrefArray[4];
const albumName = hrefArray[5];
const songName = hrefArray[6];

showSongComments();

function renderComments(response) {
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

function addComment(event) {
    const xhr = new XMLHttpRequest();
    const commentDescription = document.querySelector(`[name='commentDescription']`);
    xhr.open('POST', `/api/artists/${artistName}/${albumName}/${songName}/post-comment?commentDescription=${commentDescription.value}`, true);
    xhr.addEventListener('readystatechange', renderComments);
    xhr.send();
}

function showSongComments() {
    // instantiate request to server
    const xhr = new XMLHttpRequest();
    // open GET request to server, with request parameter of the artistName and albumName
    // from the href array
    xhr.open('GET', `/api/artists/${artistName}/${albumName}/${songName}/comments`, true)
    // xhr.open('POST', `/api/post-comment`)
    xhr.addEventListener('readystatechange', renderComments)
    xhr.send();
}

btnSubmitComment.addEventListener('click', addComment);