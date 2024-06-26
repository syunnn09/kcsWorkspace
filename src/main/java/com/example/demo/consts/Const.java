package com.example.demo.consts;

import java.util.Arrays;

public class Const {

	public static enum Report {
		WORK("work", "作業報告書");

		private String name;
		private String type;

		private Report(String type, String name) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return this.name;
		}

		public String getType() {
			return this.type;
		}

		public static boolean contains(String target) {
			return Arrays.stream(Report.values()).anyMatch(r -> r.getType().equals(target));
		}
	}
}
