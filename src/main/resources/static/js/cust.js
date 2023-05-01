
	
	 
var ctxBar = document.getElementById("bari").getContext("2d");
var myBar = new Chart(ctxBar, {
  type: 'bar',
  data: {
    labels: "[[${projetD}]]",
    datasets: [{
      label: '# of Days',
      data: "[[${delai}]]"
    }]
  },
  options: {
    responsive: true,
    barRoundness: 1,
    title: {
      display: false,
      text: "Chart.js - Bar Chart with Rounded Tops (drawRoundedTopRectangle Method)"
    },
    legend: {
      display:false
    },
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true,
          suggestedMax: 40 + 20,
          padding: 10,
        },
        gridLines: {
          drawBorder: false,
        }
      }],
      xAxes: [{
            gridLines: {
                display:false,
                drawBorder: false
            }
        }]
    }
  }
});
	
	
	