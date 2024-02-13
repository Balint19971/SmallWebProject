window.addEventListener("load", () => {
  const oldBookData = JSON.parse(localStorage.getItem("oldBookData"));
  console.log(oldBookData);

  document.querySelector("#title").value = oldBookData.title;
  document.querySelector("#page-number").value = oldBookData.pageNumber;
  document.querySelector("#publication").value = oldBookData.publishingDate;
  document.querySelector("#authors").value = oldBookData.authorsNames;

  initUpdateBtn(oldBookData.id);
});

function initUpdateBtn(updatableBookId) {
  document.querySelector("#update-btn").addEventListener("click", async () => {
    const bookParameters = {
      id: updatableBookId,
      title: document.querySelector("#title").value,
      pageNumber: document.querySelector("#page-number").value,
      publishingDate: document.querySelector("#publication").value,
      authorsNames: document.querySelector("#authors").value,
    };

    const responseFromServer = await fetch("http://localhost:8080/api/books", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(bookParameters),
    });

    if (responseFromServer.ok) {
      window.location.href = "books.html";
    }
  });
}
