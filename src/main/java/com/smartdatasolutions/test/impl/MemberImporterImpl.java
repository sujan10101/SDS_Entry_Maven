package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MemberImporterImpl implements MemberImporter {

	@Override
	public List<Member> importMembers(File inputFile) throws Exception {

		/*
		 * Implement the missing logic
		 */

		List<Member> members = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line = br.readLine();

			while (line != null) {

				String[] fields = line.split("\\s+");

				Member member = new Member();
				member.setId(fields[0]);
				member.setFirstName(fields[2]);
				member.setLastName(fields[1]);
				member.setAddress(fields[3] + " " + fields[4] + " " + fields[5]);

				if (fields.length == 11) member.setCity(fields[6] + " " + fields[7] + " " + fields[8]);
				if (fields.length == 10) member.setCity(fields[6] + " " + fields[7]);
				if (fields.length == 9) member.setCity(fields[6]);

				String stateStr = fields[fields.length - 2];

				if (stateStr.length() > 2) {
					member.setState(stateStr.substring(stateStr.length() - 2));
					member.setCity(member.getCity() + " " + stateStr.substring(0, stateStr.length() - 2));
				} else {
					member.setState(stateStr);
				}

				member.setZip(fields[fields.length - 1]);

				members.add(member);

				line = br.readLine();
			}
		}

		return members;
	}
}
