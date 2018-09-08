package com.revature.process;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ToJson {

	public static <T> String ToJson(List<T> l) {
		ObjectMapper om = new ObjectMapper();
		String ret = "";
		try {
			ret = om.writeValueAsString(l);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
