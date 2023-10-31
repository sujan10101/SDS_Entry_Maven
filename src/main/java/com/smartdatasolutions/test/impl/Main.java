package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		// TODO
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		// TODO
		return new MemberImporterImpl();
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {

		// TODO
		Set<Member> uniqueMembers = new TreeSet<>(Comparator.comparing(Member::getId));
		uniqueMembers.addAll(membersFromFile);
		return new ArrayList<>(uniqueMembers);
	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		// TODO
		return validMembers.stream()
				.collect(Collectors.groupingBy(Member::getState));

	}

	public static void main( String[] args ) {
		//TODO
		Main main = new Main();

		try {
			main.convert(new File("Members.txt"),"./", "outputFile.csv");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
