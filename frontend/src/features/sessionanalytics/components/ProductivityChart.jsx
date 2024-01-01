import Chart from "react-apexcharts";

export default function ProductivityChart({chartValues, category}) {

    const xAxisValues = chartValues.map(item => item.category);
    const yAxisValues = chartValues.map(item => item.averageProductivity);

    let yAxisLabel;

    if (category != null) {
        switch (category) {
            case 'obstacle':
                yAxisLabel = "Obstacles";
                break;
            case 'duration':
                yAxisLabel = "Duration brackets";
                break;
            case 'time':
                yAxisLabel = "Time periods";
                break;
        }
    }



    const chartData = {
        options: {
            chart: {
                type: 'bar',
                width: '100%'
            },
            plotOptions: {
                bar: {
                    horizontal: true,
                }
            },
            xaxis: {
                categories: xAxisValues,
                title: {
                    text: "Average Productivity"
                }
            },
            yaxis: {
                title: {
                    text: yAxisLabel,
                    style: {
                        whiteSpace: 'normal',
                        overflow: 'visible'
                    }
                }
            },
            colors: ["#818cf8"]
        },
        series: [
            {
                name: "Average Productivity",
                data: yAxisValues
            }
        ]
    };

    return (
        <div>
            <Chart
                options={chartData.options}
                series={chartData.series}
                type="bar"
                height={350}
                width={500}
            />
        </div>
    );
}
