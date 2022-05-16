//Init Chart Data
var chartData = [];
var issuesPortletBoundary = ".portlet-boundary_com_liferay_jira_integration_web_IssuesPortlet_";

//Chart Configurations
var chartConfigs = {
    type: "pie3d",
    renderAt: 'jira-chart-container',
    width: "100%",
    height: "350",
    dataFormat: "json",
    dataSource: {
        // Chart Configuration
        "chart": {
            "caption": "Breakdown of tasks",
            "subCaption": "by status",
            "xAxisName": "Topics",
            "yAxisName": "Number of Tickets",
            "theme": "fusion",
        },
        // Chart Data
        "data": chartData
    }
}

var jiraVizTimer;

// Test Issues Ready
AUI().ready(function () {
    jiraVizTimer = setInterval(testChartReadiness,250);
});

function testChartReadiness(){

	var issuesList = document.querySelectorAll(issuesPortletBoundary+" .lfr-status-column");

    if(issuesList && issuesList.length>0){
        clearInterval(jiraVizTimer);
        buildJiraChart();
    } else {
        //console.log("not ready");
    }
}

function buildJiraChart(){

    var statusKeys = {};
	var issuesList = document.querySelectorAll(issuesPortletBoundary+" .lfr-status-column");

	for(status in issuesList){

		var statusLabel = issuesList[status].innerHTML;

		if(statusLabel==undefined || statusLabel.trim()=="Status")
			 continue;

        if(statusKeys[statusLabel.trim()]){
            statusKeys[statusLabel.trim()] ++; 
        } else {
            statusKeys[statusLabel.trim()] = 1; 
        }
	}

    chartConfigs.dataSource.data = [];
    
    for(s in statusKeys){
        let obj = {};
        obj.value = statusKeys[s];
        obj.label = s;
        chartConfigs.dataSource.data.push(obj);
    }

    FusionCharts.ready(function(){
	    var fusioncharts = new FusionCharts(chartConfigs);
	    fusioncharts.render();
    });

}