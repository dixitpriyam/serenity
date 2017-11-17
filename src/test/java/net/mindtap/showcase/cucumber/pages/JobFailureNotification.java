package net.mindtap.showcase.cucumber.pages;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.mindtap.showcase.cucumber.utils.httpUtil.GetUtil;
import net.mindtap.showcase.cucumber.utils.mail.MailUtil;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.Serenity.SerenityConfigurer;

public class JobFailureNotification {

	private String html;

	public String fetchJobId() {
		try {
			String jobIdResponse = Serenity.sessionVariableCalled("responsebody");
			System.out.println("JOB ID json:"+jobIdResponse);
			JsonNode jobIdJson = (JsonNode) new ObjectMapper().readTree(jobIdResponse);
			JsonNode lastRunBuild = jobIdJson.get("lastBuild");
			return lastRunBuild.get("number").asText();
		} catch (Exception e) {
			System.out.println("ERROR IN FETCHING JOB ID:"+e.getMessage());
		}
		return null;
	}

	public void fetchJobStatus() {
		try {
			int i = 0;
			String jobStatus = Serenity.sessionVariableCalled("responsebody");
			JsonNode jobStatusJson = (JsonNode) new ObjectMapper().readTree(jobStatus);
			JsonNode subJobs = jobStatusJson.get("subBuilds");
			html = "<table align=\"center\" border=\"1\" cellpadding=\"2\"><tr><th>S.No</th><th>Jenkins Job Name</th><th>Status</th></tr>";
			Iterator<JsonNode> it = subJobs.iterator();
			while (it.hasNext()) {
				JsonNode job = it.next();
				String row = "<tr>";
				row = row.concat("<td>" + ++i + "</td>");
				row = row.concat("<td><a target=\"_blank\" href=\"" + ((String)Serenity.sessionVariableCalled("BASE_URL")).concat(job.get("url").asText()) + "\">"
						+ job.get("jobName") + "</a></td>");
				row = row.concat("<td>" + ("blue.png".equalsIgnoreCase(job.get("icon").asText()) ? "PASSED" : "FAILED")
						+ "</td>");
				row = row.concat("</tr>");
				html = html.concat(row);
			}
			html = html.concat("</table>");
		} catch (Exception e) {
			System.out.println("ERROR IN FETCHING JOB STATUS:"+e.getMessage());
		}
	}

	public void notifyWithMail(String[] recipients,String jobName) {

		System.out.println("recipients:"+Arrays.asList(recipients));
		MailUtil.sendMail(recipients, "automation.resultsqait@gmail.com", jobName+" :- JENKINS JOB STATUS", html);
	}

}
