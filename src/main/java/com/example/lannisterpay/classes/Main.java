package com.example.lannisterpay.classes;

import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String... args){
//		Split[] splitInfo = new Split[3];
//		splitInfo[0] = new Split(SplitType.FLAT, 45.0, "LNPYACC0019");
//		splitInfo[1] = new Split(SplitType.RATIO, 3.0, "LNPYACC0011");
//		splitInfo[2] = new Split(SplitType.PERCENTAGE, 3.0, "LNPYACC0015");
//

//		List<Split> splitInfo = new ArrayList<>(3);
//		splitInfo.add(new Split(SplitType.FLAT, 45.0, "LNPYACC0019"));
//		splitInfo.add(new Split(SplitType.RATIO, 3.0, "LNPYACC0011"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 3.0, "LNPYACC0015"));

		List<Split> splitInfo = new ArrayList<>(3);
		splitInfo.add(new Split(SplitType.FLAT, 450, "LNPYACC0019"));
//		splitInfo.add(new Split(SplitType.RATIO, 3, "LNPYACC0011"));
		splitInfo.add(new Split(SplitType.PERCENTAGE, 3, "LNPYACC0015"));
//		splitInfo.add(new Split(SplitType.RATIO, 2, "LNPYACC0016"));
//		splitInfo.add(new Split(SplitType.FLAT, 2450, "LNPYACC0029"));
		splitInfo.add(new Split(SplitType.PERCENTAGE, 10, "LNPYACC0215"));
		splitInfo.add(new Split(SplitType.FLAT, 450, "LNPYACC0019"));
//		splitInfo.add(new Split(SplitType.RATIO, 3, "LNPYACC0011"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 3, "LNPYACC0015"));
//		splitInfo.add(new Split(SplitType.RATIO, 2, "LNPYACC0016"));
//		splitInfo.add(new Split(SplitType.FLAT, 2450, "LNPYACC0029"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 10, "LNPYACC0215"));
//		splitInfo.add(new Split(SplitType.FLAT, 450, "LNPYACC0019"));
//		splitInfo.add(new Split(SplitType.RATIO, 3, "LNPYACC0011"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 3, "LNPYACC0015"));
//		splitInfo.add(new Split(SplitType.RATIO, 2, "LNPYACC0016"));
//		splitInfo.add(new Split(SplitType.FLAT, 2450, "LNPYACC0029"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 10, "LNPYACC0215"));
//		splitInfo.add(new Split(SplitType.FLAT, 450, "LNPYACC0019"));
//		splitInfo.add(new Split(SplitType.RATIO, 3, "LNPYACC0011"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 3, "LNPYACC0015"));
//		splitInfo.add(new Split(SplitType.RATIO, 2, "LNPYACC0016"));
//		splitInfo.add(new Split(SplitType.FLAT, 2450, "LNPYACC0029"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 10, "LNPYACC0215"));
//
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 3, "LNPYACC0015"));
//		splitInfo.add(new Split(SplitType.RATIO, 2, "LNPYACC0016"));
//		splitInfo.add(new Split(SplitType.FLAT, 2450, "LNPYACC0029"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 10, "LNPYACC0215"));
//		splitInfo.add(new Split(SplitType.FLAT, 450, "LNPYACC0019"));
//		splitInfo.add(new Split(SplitType.RATIO, 3, "LNPYACC0011"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 3, "LNPYACC0015"));
//		splitInfo.add(new Split(SplitType.RATIO, 2, "LNPYACC0016"));
//		splitInfo.add(new Split(SplitType.FLAT, 2450, "LNPYACC0029"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 10, "LNPYACC0215"));
//		splitInfo.add(new Split(SplitType.FLAT, 450, "LNPYACC0019"));
//		splitInfo.add(new Split(SplitType.RATIO, 3, "LNPYACC0011"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 3, "LNPYACC0015"));
//		splitInfo.add(new Split(SplitType.RATIO, 2, "LNPYACC0016"));
//		splitInfo.add(new Split(SplitType.FLAT, 2450, "LNPYACC0029"));
//		splitInfo.add(new Split(SplitType.PERCENTAGE, 10, "LNPYACC0215"));
//






//		Transaction transaction = new Transaction(1308L,12580.0,
//				"NGN", "anon8@customers.io",splitInfo);
		Transaction transaction = new Transaction(13902,4500,
				"NGN", "anon8@customers.io",splitInfo);
		Computation computation = new Computation(transaction);
		computation.compute();
		JSONPObject json = new JSONPObject(computation.toString(),computation);
		json.getSerializationType();
//		System.out.println(JSONObject.toJSONString(computation.toMap()));
		System.out.println(json.getValue());

	}


}
