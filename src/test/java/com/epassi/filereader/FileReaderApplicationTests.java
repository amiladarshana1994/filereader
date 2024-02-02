package com.epassi.filereader;

import com.epassi.filereader.dto.RequestDto;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class FileReaderApplicationTests extends BaseIntegrationTest{

	String uri = "/file-service/v1/word-count/most-frequent";
	@Test
	public void countWordsForValidUrl_smallFile_withContent_receivedResultIsCorrect() throws Exception {
		setUp();
		RequestDto request = RequestDto.builder()
				.link("https://drive.google.com/uc?export=download&id=1S9cakHFyx3i_Xydo_0v7gDUGqQpUgt_5")
				.frequentWordCount(5)
				.build();
		var closeCaseRequestJson = readFile("sample_1_response.json");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request.toString())).andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(new JSONObject(closeCaseRequestJson).toString(), new JSONObject(content).toString());
	}

	// @Test
	public void countWordsForValidUrl_largeFile_receivedResultIsCorrect() throws Exception {
		setUp();
		RequestDto request = RequestDto.builder()
				.link("https://epssi.s3.ap-southeast-2.amazonaws.com/sample_2.txt")
				.frequentWordCount(5)
				.build();
		var closeCaseRequestJson = readFile("sample_2_response.json");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request.toString())).andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(new JSONObject(closeCaseRequestJson).toString(), new JSONObject(content).toString());
	}

	@Test
	public void countWordsForValidUrl_smallFile_withoutContent_receivedResultIsCorrect() throws Exception {
		setUp();
		RequestDto request = RequestDto.builder()
				.link("https://drive.google.com/uc?export=download&id=1fMVhWFNIjQxwYErbmwfFrwAVGSfs71XH")
				.frequentWordCount(5)
				.build();
		var closeCaseRequestJson = readFile("sample_1_response.json");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request.toString())).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(new JSONObject(closeCaseRequestJson).toString(), new JSONObject(content).toString());
	}

	@Test
	public void countWordsForValidUrl_smallFile_withContent_frequentWordCountLargerThanAvailable_receivedResultIsCorrect() throws Exception {
		setUp();
		RequestDto request = RequestDto.builder()
				.link("https://drive.google.com/uc?export=download&id=1S9cakHFyx3i_Xydo_0v7gDUGqQpUgt_5")
				.frequentWordCount(100)
				.build();
		var closeCaseRequestJson = readFile("sample_1_response.json");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request.toString())).andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(new JSONObject(closeCaseRequestJson).toString(), new JSONObject(content).toString());
	}
}
