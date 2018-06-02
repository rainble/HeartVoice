$("#upload").click(function() {

    var formData = new FormData($("#uploadForm")[0]);

    formData.append("filePath", "sadasdasdas");

    $.ajax({
        url: "/yufan/upload",
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        xhrFields: {
            withCredentials: true
        },
        success: function (result) {
            alert("Success");
        },
        error: function (err) {
            alert("Upload Route List Fail.");
        }
    });
});
