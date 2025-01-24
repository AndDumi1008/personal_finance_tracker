function handleCheckboxChange() {
    const checkboxes = document.querySelectorAll('input[type="checkbox"][name="selectedOperation"]');
    const selectedCheckboxes = Array.from(checkboxes).filter(checkbox => checkbox.checked);
    const editButton = document.getElementById("editButton");
    const deleteButton = document.getElementById("deleteButton");
    const errorMessage = document.getElementById("error-message");

    if (selectedCheckboxes.length === 1) {
        editButton.disabled = false;
        deleteButton.disabled = false;
        errorMessage.innerText = "";
    } else if (selectedCheckboxes.length > 1) {
        editButton.disabled = true;
        deleteButton.disabled = false;
        errorMessage.innerText = "You can edit only one transaction at a time";
    } else {
        editButton.disabled = true;
        deleteButton.disabled = true;
        errorMessage.innerText = "";
    }
}

function editTransaction() {
    const selectedCheckbox = document.querySelector('input[type="checkbox"][name="selectedOperation"]:checked');
    if (selectedCheckbox) {
        const operationId = selectedCheckbox.value;
        window.open(`/operation/edit/${operationId}`, 'pagename', 'resizable,height=500,width=1000');
    }
}

function handleResponse(event) {
            event.preventDefault();
            const form = event.target;
            const formData = new FormData(form);
            fetch(form.action, {
                method: form.method,
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("Transaction has been saved");
                    window.close();
                } else {
                    document.getElementById("error-message").innerText = data.message;
                }
            })
            .catch(error => {
                document.getElementById("error-message").innerText = "An error occurred while saving the transaction.";
            });
        }

function markForDelete() {
    const selectedCheckboxes = document.querySelectorAll('input[type="checkbox"][name="selectedOperation"]:checked');
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    if (!csrfHeader || !csrfToken) {
        console.error('CSRF token or header not found');
        return;
    }

    const headers = new Headers();
    headers.append(csrfHeader, csrfToken);

    const totalSelected = selectedCheckboxes.length;
    let deletedCount = 0;

    selectedCheckboxes.forEach(checkbox => {
        const operationId = checkbox.value;
        console.log(`Marking operation with ID: ${operationId} for deletion`); // Debugging log

        fetch('/markAsRemoved', {
            method: 'POST',
            headers: headers,
            body: new URLSearchParams({ id: operationId })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                deletedCount++;
                if (deletedCount === totalSelected) {
                    alert(`${deletedCount} transaction(s) marked for deletion`);
                    location.reload();
                }
            } else {
                document.getElementById("error-message").innerText = data.message;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById("error-message").innerText = `An error occurred while marking the transaction for deletion: ${error.message}`;
        });
    });
}