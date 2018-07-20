const btnSubmitComment = document.getElementById('submitButton');
const albumCommentsUl = document.querySelector('.albumComments');

const hrefArray = document.URL.split("/");
// hrefArray[1] states, hrefArray[2] ${state.abbreviation}
// grab artist name
const artistName = hrefArray[4];
const albumName = hrefArray[5];

showAlbumComments();

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
        albumCommentsUl.innerHTML = commentHTML;
    }
}

function addComment(event) {
    const xhr = new XMLHttpRequest();
    const commentDescription = document.querySelector(`[name='commentDescription']`);
    xhr.open('POST', `/api/artists/${artistName}/${albumName}/post-comment?commentDescription=${commentDescription.value}`, true);
    xhr.addEventListener('readystatechange', renderComments);
    xhr.send();
}

function showAlbumComments() {
    // instantiate request to server
    const xhr = new XMLHttpRequest();
    // grab url from current document
    // i.e. localhost.../artists/artistName/album

    // open GET request to server, with request parameter of the artistName and albumName
    // from the href array
    xhr.open('GET', `/api/artists/${artistName}/${albumName}/comments`, true)
    // xhr.open('POST', `/api/post-comment`)
    xhr.addEventListener('readystatechange', renderComments)
    xhr.send();
}

btnSubmitComment.addEventListener('click', addComment);