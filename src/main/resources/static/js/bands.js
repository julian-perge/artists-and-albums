function renderBands(response) {
  console.log(response);
  // Sorry Donny, triple-equals is best equals
  if (this.status === 200 && this.readyState === 4) {
    const bandsUl = document.querySelector('.bandsList');
    const remainingBands = JSON.parse(response.currentTarget.response);
    let listOfBands = '';
    remainingBands.forEach((band) => {
      listOfBands += `
        <li>
            <a href="/band/${band.bandName}">
                ${band.bandName}
            </a>
            <button class="deleteButton">DELETE</button>
        </li>`;
    });
    bandsUl.innerHTML = listOfBands;
  }
}

function addBand() {
  const xhr = new XMLHttpRequest();
  const bandName = document.querySelector('[name="bandName"]');
  const recordLabel = document.querySelector('[name="recordLabel"]');
  xhr.open('POST', `/api/bands?bandName=${bandName.value}&recordLabel=${recordLabel.value}`, true);
  xhr.addEventListener('readystatechange', renderBands);
  bandName.value = '';
  recordLabel.value = '';
  xhr.send();
}

function showBands() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/bands', true);
  xhr.addEventListener('readystatechange', renderBands);
  xhr.send();
}

function deleteBand(event) {
  if (event.target.classList.contains('deleteButton')) {
    const deleteButton = event.target;
    // previous sibling being the anchor
    const bandContainer = deleteButton.previousElementSibling;
    const bandHref = bandContainer.getAttribute('href').split('/');
    const bandName = bandHref[2];

    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', `/api/band/delete?bandName=${bandName}`, true);
    xhr.addEventListener('readystatechange', renderBands);
    xhr.send();
  }
}

showBands();
const btnSubmitBand = document.getElementById('submitButton');
const bandsUl = document.querySelector('.bandsList');
btnSubmitBand.addEventListener('click', addBand);
bandsUl.addEventListener('click', deleteBand);