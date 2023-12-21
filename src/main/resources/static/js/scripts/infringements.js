const url = "/adminsys/api/infringements";

function save(flag) {
	$("#modal-update").modal("hide")
	let id = $("#save").data("id");
	let infringement = {
		id: id,
		dni: $("#dni").val(),
		plate: $("#plate").val(),
		description: $("#description").val(),
		infringement: $("#infringement").val(),
		dat: $("#dat").val(),
	}

	let method = flag == 1 ? "POST" : "PUT";
	$.ajax({
		type: method,
		url: url,
		data: JSON.stringify(infringement),
		dataType: "text",
		contentType: "application/json",
		cache: false,
		success: function(data) {
			if (data == 0) {
				Swal.fire({
					icon: 'error',
					title: 'The infringement is already registered!',
					showConfirmButton: false,
					timer: 1500
				})
			} else {
				let texto = flag == 1 ? "saved" : "updated";
				getTable();
				Swal.fire({
					icon: 'success',
					title: 'The infringement was ' + texto + '.',
					showConfirmButton: false,
					timer: 1500
				})
				clear();
			}
		},
	}).fail(function() {

	});
}

function deleteRow(id) {
	$.ajax({
		type: "DELETE",
		url: url + `/${id}`,
		data: {
			id: id,
		},
		cache: false,
		timeout: 600000,
		success: function(data) {
			Swal.fire({
				icon: 'success',
				title: 'The infringement was deleted!',
				showConfirmButton: false,
				timer: 1500
			});
			getTable();
		},
	}).fail(function() {

	});

}


function getTable() {

	$.ajax({
		type: "GET",
		url: url,
		dataType: "text",
		cache: false,
		success: function(data) {
			let t = $("#tableInfringements").DataTable();
			t.clear().draw(false);

			let buttons = '<button type="button" class="btn btn-warning btn-sm update"><i class="fas fa-edit"></i> </button>' +
				'<button type="button" class="btn btn-danger btn-sm delete"><i class="fas fa-trash"></i></button>';

			let js = JSON.parse(data);

			if (js.hasOwnProperty("body")) {
				$.each(js.body, function(a, b) {
					t.row.add([b.id, b.dni, b.dat, b.plate, b.infringement, b.description, buttons]);

				});
			}
			t.draw(false);

		},
	}).fail(function() {

	});
}

function getRow(id) {

	$.ajax({
		type: "GET",
		url: url + `/${id}`,
		data: {
			id: id,
		},
		cache: false,
		timeout: 600000,
		success: function(data) {
			var dateObject = new Date(data.body.dat);
			var formattedDate = dateObject.toISOString();
			$("#modal-title").text("Edit Infringement");
			$("#dni").val(data.body.dni);
			$("#dat").val(formattedDate);
			$("#infringement").val(data.body.infringement);
			$("#plate").val(data.body.plate);
			$("#description").val(data.body.description);
			$("#save").data("flag", 0);
			$("#modal-update").modal("show");
		},
	}).fail(function() {

	});
}

function clear() {
	$("#modal-title").text("New Infringement");
	$("#dni").val("");
	$("#save").data("id", 0);
	$("#save").data("flag", 1);
}

$(document).ready(function() {

	$("#tableInfringements").DataTable({
		language: {
			lengthMenu: "Show _MENU_ records",
			zeroRecords: "There are not matches!",
			info: "Showing from _START_ to _END_ of _TOTAL_",
			infoEmpty: "No results!",
			search: "Search: ",
			paginate: {
				first: "First",
				last: "Last",
				next: "Next",
				previous: "Previous",
			},
		},
		columnDefs: [
			{ targets: 0, width: "10%" },
			{ targets: 1, width: "20%" },
			{ targets: 2, width: "20%" },
			{ targets: 3, width: "10%" },
			{ targets: 4, width: "10%" },
			{ targets: 5, width: "20%" },
			{ targets: 6, orderable: false, width: "10%" }
		],
	});

	clear();

	$("#new").click(function() {
		clear();
	});

	$("#save").click(function() {

		let flag = $("#save").data("flag");
		save(flag);
	})

	$(document).on('click', '.delete', function() {
		Swal.fire({
			title: 'Delete infringement',
			text: "¿Are you sure you want to delete this infringement?",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Si'
		}).then((result) => {
			if (result.isConfirmed) {
				let id = $($(this).parents('tr')[0].children[0]).text();
				deleteRow(id);
			}
		})
	});

	$(document).on('click', '.update', function() {
		let id = $($(this).parents('tr')[0].children[0]).text();
		getRow(id);
		$("#save").data("id", id);
	});
	getTable();
});