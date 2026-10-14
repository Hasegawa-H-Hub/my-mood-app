const checkboxes = document.querySelectorAll(
    '#moodFilter input[type="checkbox"]'
);
 
document.getElementById("selectAll").addEventListener("click", () => {
    checkboxes.forEach(checkbox => {
        checkbox.checked = true;
    });
});
 
document.getElementById("deselectAll").addEventListener("click", () => {
    checkboxes.forEach(checkbox => {
        checkbox.checked = false;
    });
});
 