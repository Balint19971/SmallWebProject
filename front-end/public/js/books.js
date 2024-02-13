async function initBooksTable() {
  const dbResponse = await fetch("http://localhost:8080/api/books", {
    method: "GET",
  });
  if (dbResponse.ok) {
    const books = await dbResponse.json();
    const booksTableRows = document.querySelector("#books");
    let booksHTML = "";

    books.forEach((book) => {
      booksHTML += `
                    <tr id="tr${book.id}">
                        <td>${book.title}</td>
                        <td>${book.pageNumber}</td>
                        <td>${book.publishingDate}</td>
                        <td>${book.authorsNames}</td>
                        <td>
                            <button class="btn btn-info btn-sm update-btn" data-id="${book.id}">Update</button>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="${book.id}">Delete</button>
                        </td>
                    </tr>
                `;
    });
    booksTableRows.innerHTML = booksHTML;
    initUpdateBtns();
    initDeleteBtns();
  }
}

function initDeleteBtns() {
  const deleteBtns = document.querySelectorAll(".delete-btn");

  deleteBtns.forEach((deleteBtn) => {
    deleteBtn.addEventListener("click", async (event) => {
      const deletableBookId = event.target.dataset.id;

      const deleteResponse = await fetch(
        `http://localhost:8080/api/books/${deletableBookId}`,
        {
          method: "DELETE",
        }
      );
      if (deleteResponse.ok) {
        initBooksTable();
      }
    });
  });
}
function initUpdateBtns() {
  const updateBtns = document.querySelectorAll(".update-btn");

  updateBtns.forEach((updateBtn) => {
    updateBtn.addEventListener("click", (event) => {
      const updatableBookId = event.target.dataset.id;
      const bookTableRow = document.querySelector(`#tr${updatableBookId}`);
      const tdElements = bookTableRow.querySelectorAll("td");

      oldBookData = {
        id: updatableBookId,
        title: tdElements[0].textContent,
        pageNumber: tdElements[1].textContent,
        publishingDate: tdElements[2].textContent,
        authorsNames: tdElements[3].textContent,
      };

      localStorage.setItem("oldBookData", JSON.stringify(oldBookData));

      window.location.href = "update-form.html";
    });
  });
}

window.addEventListener("load", () => {
  initBooksTable();
});
