const createBtn = document.querySelector("#create-btn");

createBtn.addEventListener("click", async () => {
  const bookParameters = {
    title: document.querySelector("#title").value,
    pageNumber: document.querySelector("#page-number").value,
    publishingDate: document.querySelector("#publication").value,
    authorsNames: document.querySelector("#authors").value,
  };

  const responseFromServer = await fetch("http://localhost:8080/api/books", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(bookParameters),
  });

  if (responseFromServer.ok) {
    window.location.href = "books.html";
  }
});
