$(document).ready(function() {
	
	var count_x = delaiprojet.map(x => x[0])
	var count_y = delaiprojet.map(x => x[1])
	
	console.log(count_x)
	console.log(count_y)
	
	var barChartData = {
		labels:count_x,
		datasets:[
			{
				fillcolor:"rgba(220,220,220,0.5)",
				strokecolor:"rgba(220,220,220,1)",
				data:count_y
			},
			{
				fillcolor:"rgba(220,220,220,0.5)",
				strokecolor:"rgba(220,220,220,1)",
				data:[1,2,4,3,1,5,2]
			}
		]
	}
	
	const ctx = document.getElementById('myChart').getContext('2d');
 
var count_x = delaiprojet.map(x => x[0]);
var count_y = delaiprojet.map(x => x[1]);

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: "[[${count_x}]]",
      datasets: [{
        label: '# of Days',
        data: "[[${count_y}]]",
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
	
	
	
	
})