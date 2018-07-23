function listRecordLabels(response) {
  if (this.status === 200 && this.readyState === 4) {
    const datalistRecordLabels = document.getElementById('listRecordLabels');
    const recordLabelsTolist = JSON.parse(response.currentTarget.response);
    let optionList = '';
    recordLabelsTolist.forEach((recordLabel) => {
      optionList += `
        <option value="${recordLabel.labelName}" />
      `;
    });
    datalistRecordLabels.innerHTML = optionList;
  }
}

function showRecordLabels() {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/recordLabels', true);
  xhr.addEventListener('readystatechange', listRecordLabels);
  xhr.send();
}

showRecordLabels();