function listBands(response) {
  if (this.status === 200 && this.readyState === 4) {
    const dataListBands = document.getElementById('listBands');
    const bandsToList = JSON.parse(response.currentTarget.response);
    let optionList = '';
    bandsToList.forEach((band) => {
      optionList += `
        <option value="${band.bandName}" />
      `;
    });
    dataListBands.innerHTML = optionList;
  }
}

function showBandsInInput() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/bands', true);
  xhr.addEventListener('readystatechange', listBands);
  xhr.send();
}

showBandsInInput();