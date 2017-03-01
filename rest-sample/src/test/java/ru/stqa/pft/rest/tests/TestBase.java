package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {


    public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException {
        return !getIssueStatus(issueId).equals("Closed");
    }


    public int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public String getIssueStatus(int id) {
        String json = RestAssured.get(String.format("http://demo.bugify.com/api/issues/%s.json", id)).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement el = issues.getAsJsonArray().get(0);
        return el.getAsJsonObject().get("state_name").getAsString();
    }
}
