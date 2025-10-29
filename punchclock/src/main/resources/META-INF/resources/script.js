const URL = 'http://localhost:8080';
let entries = [];

let categories = [];
let tags = [];

const indexCategories = () => {
    fetch(`${URL}/categories`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            categories = result;
            renderCategorySelect();
        });
    });
};

const indexTags = () => {
    fetch(`${URL}/tags`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            tags = result;
        });
    });
};

const renderCategorySelect = () => {
    const select = document.getElementById('category');
    select.append(
        ...categories.map((c) => {
            const option = document.createElement('option');
            option.text = c.title;
            option.value = c.id;
            return option;
        })
    );
}

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    entry['category'] = {
        "id": parseInt(formData.get('category'))
    };

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const deleteButton = document.createElement('button');
        deleteButton.innerText = "Delete";
        deleteButton.onclick = () => {
            fetch(`${URL}/entries/${entry.id}`, {
                method: 'DELETE',
            }).then(() => {
                indexEntries()
            });
        }
        console.log(deleteButton);

        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(deleteButton)
        display.appendChild(row);
    });
};

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexCategories();
    indexEntries();
});