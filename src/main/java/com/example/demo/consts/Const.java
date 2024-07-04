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

	public static enum TimecardStatus {
		LEAVING("leaving", ""),
		ATWORK("atwork", "出勤中"),
		REST("rest", "休憩中");

		private String status;
		private String statusString;
		private TimecardStatus(String status, String str) {
			this.status = status;
			this.statusString = str;
		}
		public String getStatus() {
			return this.status;
		}
		public String getStatusString() {
			return this.statusString;
		}

		public static TimecardStatus getStatus(String status) {
			for (TimecardStatus stat : TimecardStatus.values()) {
				if (stat.status.equals(status)) {
					return stat;
				}
			}
			return LEAVING;
		}
	}
}
