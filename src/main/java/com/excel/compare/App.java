package com.excel.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class App 
{
	static LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> excel1=new LinkedHashMap<String, LinkedHashMap<Integer, List<String>>>();
	static LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> excel2=new LinkedHashMap<String, LinkedHashMap<Integer, List<String>>>();
	public static void main( String[] args )
    {
        System.out.println( "File Comparison!" );
//	      String csvLoc1=args[0];
//	      String csvLoc2=args[1];
        
     String csvLoc1 = "C:\\Users\\kritika.dwivedi\\Documents\\guestuser.csv";
       String csvLoc2 = "C:\\Users\\kritika.dwivedi\\Documents\\reguser.csv";
        
        String fileLoc1 = "";
        String xlsLoc1 = csvLoc1.substring(0,csvLoc1.lastIndexOf("\\"));
        String fileName1= csvLoc1.substring(csvLoc1.lastIndexOf("\\"), csvLoc1.lastIndexOf("."))+"Excel";
        fileLoc1 = CsvToExcel.convertCsvToXls(xlsLoc1, csvLoc1, fileName1);
        System.out.println("File1 Location Is?= " + fileLoc1);	
        
        String fileLoc2 = "";
        String xlsLoc2 = csvLoc2.substring(0,csvLoc2.lastIndexOf("\\"));
        String fileName2= csvLoc2.substring(csvLoc2.lastIndexOf("\\"), csvLoc2.lastIndexOf("."))+"Excel";
        fileLoc2 = CsvToExcel.convertCsvToXls(xlsLoc2, csvLoc2, fileName2);
        System.out.println("File2 Location Is?= " + fileLoc2);	

        
        excel1=ExcelRead.loadExcelFileData(fileLoc1);
        excel2=ExcelRead.loadExcelFileData(fileLoc2);
        
      //  comparison(excel1,excel2);
        comparisonSpecific2(excel1,excel2);
        
    }
	
	private static void comparison(LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> excel12,
			LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> excel22) {
		//String columnName="A";
		
		LinkedHashMap<Integer, List<String>> internalMap1=excel12.get("Sheet");
		LinkedHashMap<Integer, List<String>> internalMap2=excel22.get("Sheet");
		System.out.println(internalMap1.size());
		System.out.println(internalMap2.size());
		
		if (internalMap1.size()==internalMap2.size()) {
		System.out.println();
		System.out.println("************** Mimatches in Files *****************");
		for(int i=0;i<internalMap1.size();i++) {
		List<String> rows1=internalMap1.get(i);
		List<String> rows2=internalMap2.get(i);
		
		for (int j=0;j<rows1.size();j++) {
			if(!(rows1.get(j).equals(rows2.get(j)))) {
				System.out.println("Data mismatch found at "+"'"+(char)(j+65)+(i+1)+"'"+" cell: "
						+ "File1 contain "+"'"+rows1.get(j)+"'"+" while File2 contain "+"'"+rows2.get(j)+"'");
			}
		}
		}
		}
		
		if (internalMap1.size()<internalMap2.size()) {
			System.out.println();
			System.out.println("************** Mimatches in Files *****************");
			for(int i=internalMap1.size()+11;i<internalMap2.size()+1;i++) {
			List<String> rows1=internalMap1.get(i);
			List<String> rows2=internalMap2.get(i);
			
			for (int j=0;j<rows1.size();j++) {
				if(!(rows1.get(j).equals(rows2.get(j)))) {
					System.out.println("Data mismatch found at "+"'"+(char)(j+65)+(i+1)+"'"+" cell: "
							+ "File1 contain "+"'"+rows1.get(j)+"'"+" while File2 contain "+"'"+rows2.get(j)+"'");
				}
			}
			System.out.println("Row "+i+" is missing in File1");
			}
			}
		
	}
	
	private static void comparisonSpecific(LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> excel12,
			LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> excel22) {
		//String columnName="A";
		
		LinkedHashMap<Integer, List<String>> internalMap1=excel12.get("Sheet");
		LinkedHashMap<Integer, List<String>> internalMap2=excel22.get("Sheet");
		
		System.out.println();
		System.out.println("************** Mimatches for Parameter 'PageName' *****************");
		List<String> pageName1=internalMap1.get(15);
		List<String> pageName2=internalMap2.get(15);
		for (int j=0;j<pageName1.size();j++) {
			
			if(!(pageName1.get(j).equals(pageName2.get(j)))) {
				System.out.println("Data mismatch found at "+"'"+(char)(j+65)+(16)+"'"+" cell");
				System.out.println("File1 contains: "+"'"+pageName1.get(j)+"'");
				System.out.println("File2 contains: "+"'"+pageName2.get(j)+"'");
				System.out.println();
			}
		}
		
		System.out.println();
		System.out.println("************** Mimatches for Parameter 'Events' *****************");
		List<String> events1=internalMap1.get(29);
		List<String> events2=internalMap2.get(29);
		for (int j=0;j<events1.size();j++) {
			String mis1="";
			String mis2="";
			if(!(events1.get(j).equals(events2.get(j)))) 
			{
				System.out.println();
				System.out.println("Data mismatch found at "+"'"+(char)(j+65)+(30)+"'"+" cell: "
						+ "See differences below:");
				
				mis1=events1.get(j);
				mis2=events2.get(j);
				String misArr1[]=mis1.split(",");
				String misArr2[]=mis2.split(",");
				List<String> al1 = new ArrayList<String>();
				al1 = Arrays.asList(misArr1);
				List<String> al2 = new ArrayList<String>();
				al2 = Arrays.asList(misArr2);
				System.out.println("File1 contains: "+al1);
				System.out.println("File2 contains: "+al2);
				for (String s: al2) {      // go through all in second list
				    if (! al1.contains(s)) {  // if string not in master list
				        System.out.println("Cell1 missing data: "+s); // print that string
				    }
				}
				
				for (String s: al1) {      // go through all in second list
				    if (! al2.contains(s)) {  // if string not in master list
				        System.out.println("Cell2 missing data: "+s); // print that string
				    }
				}
			
			}
		}
		
		System.out.println();
		System.out.println("************** Mimatches for Parameter 'Products' *****************");
		List<String> products1=internalMap1.get(30);
		List<String> products2=internalMap2.get(30);

		for (int j=0;j<products1.size();j++) {
			String mis1="";
			String mis2="";
			if(!(products1.get(j).equals(products2.get(j)))) 
			{
				System.out.println();
				System.out.println("Data mismatch found at "+"'"+(char)(j+65)+(31)+"'"+" cell: "
						+ "See differences below:");
				
				mis1=products1.get(j);
				mis2=products2.get(j);
				String misArr1[]=mis1.split("[|;]");
				String misArr2[]=mis2.split("[|;]");
				List<String> al1 = new ArrayList<String>();
				al1 = Arrays.asList(misArr1);
				List<String> al2 = new ArrayList<String>();
				al2 = Arrays.asList(misArr2);
				System.out.println("File1 contains: "+al1);
				System.out.println("File2 contains: "+al2);
					for (String s: al2) {      // go through all in second list
					    if (! al1.contains(s)) {  // if string not in master list
					        System.out.println("Cell1 missing data: "+s); // print that string
					    }
					}

				for (String s: al1) {      // go through all in second list
				    if (! al2.contains(s)) {  // if string not in master list
				        System.out.println("Cell2 missing data: "+s); // print that string
				    	}
					}
			}
		}
	}
	
	private static void comparisonSpecific2(LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> excel12,
			LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> excel22) {
		//String columnName="A";
		String param[]= {"pageName","events","products"};
		for(int para=0;para<param.length;para++) {
		LinkedHashMap<Integer, List<String>> internalMap1=excel12.get("Sheet");
		LinkedHashMap<Integer, List<String>> internalMap2=excel22.get("Sheet");
		
		System.out.println();
		System.out.println("************** Mimatches for Parameter '"+param[para]+"' *****************");
		List<String> paraName1=new ArrayList<String>();
		List<String> paraName2=new ArrayList<String>();
		int k,l,m;
		List<String> misscol1 = new ArrayList<String>();
		List<String> misscol2 = new ArrayList<String>();
		List<String> m1 = new ArrayList<String>();
		List<String> m2 = new ArrayList<String>();
		List<String> cols1=new ArrayList<String>();
		List<String> cols2=new ArrayList<String>();
		for(String t:internalMap1.get(0)) {
			cols1.add(t);
		}
		for(String t:internalMap2.get(0))
			cols2.add(t);

		
		for(k=0;k<internalMap1.size();k++)
		{ if (internalMap1.get(k).contains(param[para])) {
			paraName1=internalMap1.get(k);
			System.out.println("Comparison of row "+(k+1)+" from File1");
		}}
		for(l=0;l<internalMap2.size();l++)
		{ if (internalMap2.get(l).contains(param[para])) {
			paraName2=internalMap2.get(l);
			System.out.println("Comparison of row "+(l+1)+" from File2");
			
		}}
		System.out.println();
		m=0;
		for (String s: internalMap1.get(0)) {      // go through all in second list
		    if (! internalMap2.get(0).contains(s)) {  // if string not in master list
		        System.out.println("File2 missing column: "+s+" at "+(char)(65+m)+(1)); // print that string
		       m1.add(Integer.toString(m));
		        misscol1.add(s);
		       
		    }
		    m=m+1;
		}
		m=0;
		for (String s: internalMap2.get(0)) { // go through all in second list
		    if (! internalMap1.get(0).contains(s)) {  // if string not in master list
		        System.out.println("File1 missing column: "+s+" at "+(char)(65+m)+(1)); // print that string  
		       m2.add(Integer.toString(m));
		        misscol2.add(s);
		    }
		    m=m+1;
		}
		System.out.println();
		for(int miss=0;miss < misscol1.size();miss++) {
			String index=misscol1.get(miss);
			cols1.remove(index);
		}

		for(int miss=m1.size()-1; miss >=0; miss--) {
			int index=Integer.parseInt(m1.get(miss));
			paraName1.remove(index);
		}
		

		for(int miss=0;miss < misscol2.size();miss++) {
			String index=misscol2.get(miss);
			cols2.remove(index);
		}
		for(int miss=m2.size()-1; miss >=0; miss--) {
			String index=m2.get(miss);
			paraName2.remove(index);
		}

		for (int j=0;j<paraName1.size();j++) {
			if(!(paraName1.get(j).equals(paraName2.get(j)))) {
				System.out.println("Data mismatch found at "+"'"+cols1.get(j)+"'"+" column");

				System.out.println();
				
				String mis1=paraName1.get(j);
				String mis2=paraName2.get(j);
				String misArr1[]=null;
				String misArr2[]=null;
				if(param[para].equals("events")) {
					misArr1=mis1.split("[,]");
					misArr2=mis2.split("[,]");
				}
				else if(param[para].equals("products"))
				 {
					misArr1=mis1.split("[|;]");
					misArr2=mis2.split("[|;]");
				}
				else
				{
					misArr1=mis1.split("/");
					misArr2=mis2.split("/");
				}
				
				List<String> al1 = new ArrayList<String>();
				al1 = Arrays.asList(misArr1);
				List<String> al2 = new ArrayList<String>();
				al2 = Arrays.asList(misArr2);
				System.out.println("File1 contains: "+al1);
				System.out.println("File2 contains: "+al2);
				System.out.println();
				if(((al1.size()!=1) || (!al1.get(0).isEmpty()))  && ((al2.size()!=1) || (!al1.get(0).isEmpty()))) {
					for (String s: al2) {      // go through all in second list
					    if (! al1.contains(s)) {  // if string not in master list
					        System.out.println("File1 missing data for "+cols1.get(j)+" : " +s); // print that string
					    }
					}
				
				for (String s: al1) {      // go through all in second list
				    if (! al2.contains(s)) {  // if string not in master list
				        System.out.println("File2 missing data for "+cols1.get(j)+" : " +s); // print that string
				    	}
					}
				}
				System.out.println();
			}
		}
		}
	}
	
}
