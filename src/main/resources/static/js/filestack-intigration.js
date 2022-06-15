"use strict";
const client = filestack.init(FS_API_KEY);

// options for picking single image
const options1 = {
    fromSources: ["local_file_system", "url"],
    accept: ["image/*"],
    onUploadDone: function (res) {$("#img").val(res.filesUploaded[0].url)
        console.log(res.filesUploaded[0].url)},
}

// options for picking multiple images
const options2 = {
    fromSources: ["local_file_system", "url"],
    accept: ["image/*"],
    maxFiles: 4,
    onUploadDone: function (res) {
        const imgFiles = [];

        res.filesUploaded.forEach((file) => {
            imgFiles.push(file.url)
        })

        $("#img").val(imgFiles);
    }
}

// single image selector
$("#upload-img").click(function(){
    client.picker(options1).open();
});

// multiple image selector
$("#upload-multipleImg").click(function(){
    client.picker(options2).open();
});