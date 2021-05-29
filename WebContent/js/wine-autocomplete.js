/**
 * 
 */
const dataListID = "#wineQueryOptions";

function onWineSearchChange(url, text) {
	const { options } = $("datalist")[0];

	if (options)
		for (let i = 0; i < options.length; i++)
			if (options[i].value === text) $("#autocompleteWineForm").submit();

	$(dataListID).html("<option value=\"...\">...</option>");

	$.ajax({
		dataType: 'json',
		url,
		method: "GET",
		data: { q: text }
	})
		.done((data) => {
			$(dataListID).html("");
			const options = [];

			data.forEach(wine => {
				console.log(wine);

				const optionElement =
					$("<option></option>")
						.val(wine.wine)
						.text(wine.winery + ", " + wine.winetype + ", " + wine.winecolor);

				options.push(optionElement);
			});

			$(dataListID).append(options);
		})
		.fail((xhr, textStatus) => console.log(xhr, textStatus))
		.always(() => console.log("done"));
}