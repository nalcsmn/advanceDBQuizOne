package Main;


import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;


import modelo.Details;

public class MainRun {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {

		choyses();


	}

	private static void choyses() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("Lacsmana,Neil");
		System.out.println("****welcome***");
		System.out.println("");
		System.out.println("[1] Insert");
		System.out.println("[2] Select");
		System.out.println("[3] Modify");
		System.out.println("[4] Close the Program");
		System.out.println("");
		System.out.println("**************");

		int choice = in.nextInt();
		if (choice == 1) {
			Insert();

		} else if (choice == 2) {

			Select();
		} else if (choice == 3) {

			Modify();

		} else {
			System.out.print("Invalid");
			choyses();
		}

	}

	private static void Modify() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("**************");
		System.out.println("Enter the Following");
		System.out.println("id");
		int idmod = in.nextInt();
		System.out.println("lastname: ");
		String lastNamemod  = in.next();
		System.out.println("firstname: ");
		String firstNamemod  = in.next();
		System.out.println("middleName: ");
		String middleNamemod  = in.next();
		System.out.println("Age: ");
		int edadmod  = in.nextInt();
		System.out.println("");
		System.out.println("**************");
		System.out.println("");

		int fibList[] = fibSequence(edadmod);
		//		System.out.println(fibList);
		int results[] = checkEven(fibList);
		//		System.out.println(results);

		int even = results[0];
		int odd = results[1];

		System.out.println(even + "");
		System.out.println(odd + "");

		if(even >=10) {
			Session session = dbutil.getSession();
			Transaction tx = null;

			try {

				String sql = "select * from details where userId = :userId";

				Query query = session.createSQLQuery(sql);
				System.out.println("Update condition EVEN");
				query.setParameter("userId",idmod);
				query.setResultTransformer(Transformers.aliasToBean(Details.class));

				Details INfoResult = (Details) query.uniqueResult();

				//				System.out.println("update successfully: " + INfoResult.getUserId());

				tx = session.beginTransaction();

				INfoResult.setLastName(lastNamemod);
				INfoResult.setFirstName(firstNamemod);
				INfoResult.setMiddleName(middleNamemod);
				INfoResult.setEdad(edadmod);

				session.update(INfoResult);
				tx.commit();

			} catch (Exception ex) {
				if (tx != null) {
					tx.rollback();
				}
				System.out.println("NO EXISTING DATA FOUND");
				ex.printStackTrace();
			} finally {
				session.close();
			}		
			choyses();

		}
		else if(even < 10) {
			Session session = dbutil.getSession();
			Transaction tx = null;
			try {

				String sql = "select * from details where userId = :userId";
				Query query = session.createSQLQuery(sql);
				query.setParameter("userId", idmod);
				query.setResultTransformer(Transformers.aliasToBean(Details.class));

				Details INfoResult = (Details) query.uniqueResult();
				System.out.println("Delete Successfully!");

				tx = session.beginTransaction();
				session.delete(INfoResult);
				// or
				// Student stud = (Student) session.get(Student.class, 10);
				// session.delete(stud);
				tx.commit();


			} catch (Exception ex) {
				if (tx != null) {
					tx.rollback();
				}
				System.out.println("NO Data FOUND TO BE DELETED");
				ex.printStackTrace();
			} finally {
				session.close();
			}		
			choyses();

		}
		else if(odd <= 10) {
			Session session = dbutil.getSession();
			Transaction tx = null;


			try {

				String sql = "select * from details where userId = :userId";
				Query query = session.createSQLQuery(sql);
				query.setParameter("userId", idmod);
				query.setResultTransformer(Transformers.aliasToBean(Details.class));

				Details INfoResult = (Details) query.uniqueResult();

				System.out.println("Delete Successfully!");

				tx = session.beginTransaction();
				session.delete(INfoResult);
				// or
				// Student stud = (Student) session.get(Student.class, 10);
				// session.delete(stud);
				tx.commit();


			} catch (Exception ex) {
				if (tx != null) {
					tx.rollback();
				}
				System.out.println("NO Data FOUND TO BE DELETED");
				ex.printStackTrace();
			} finally {
				session.close();
			}		
			choyses();

		}
		else if(odd >= 10) {

			System.out.println("Updating");
			Session session = dbutil.getSession();
			Transaction tx = null;

			try {

				String sql = "select * from details where userId = :userId";

				Query query = session.createSQLQuery(sql);
				System.out.println("Update condition ODD");
				query.setParameter("userId",idmod);
				query.setResultTransformer(Transformers.aliasToBean(Details.class));

				Details INfoResult = (Details) query.uniqueResult();

				//				System.out.println("update successfully: " + INfoResult.getUserId());

				tx = session.beginTransaction();

				INfoResult.setLastName(lastNamemod);
				INfoResult.setFirstName(firstNamemod);
				INfoResult.setMiddleName(middleNamemod);
				INfoResult.setEdad(edadmod);

				session.update(INfoResult);
				tx.commit();

			} catch (Exception ex) {
				if (tx != null) {
					tx.rollback();
				}
				System.out.println("NO EXISTING DATA FOUND");
				ex.printStackTrace();
			} finally {
				session.close();
			}		
			choyses();

		}

	}
	private static int[] fibSequence(int edadIns) {

		int num1 = 0;
		int num2 = 1;
		int num3 = 0;
		int fibNumbers[] = new int[edadIns];

		for(int i = 0; i < edadIns; i ++) {

			num3 = num1;
			num1 = num1 + num2;
			num2 = num3;
			fibNumbers[i] = num1;
		}

		return fibNumbers;

	}
	private static int[] checkEven(int [] fibNumbers) {
		int results[] = new int[2];
		int even = 0;
		int odd = 0;
		for(int i = 0; i < fibNumbers.length;i ++) {

			if(fibNumbers[i] % 2 == 0) {
				even +=1;

			}else {
				odd += 1;
			}
		}

		results[0] = even;
		results[1] = odd;


		return results;
	}

	private static void Select() {
		// TODO Auto-generated method stub


		System.out.println("id");
		int idz = in.nextInt();
		System.out.println("lastname");
		String lname = in.next();
		System.out.println("loading");

		Session session = dbutil.getSession();
		Transaction tx = null;
		try {
			String sql = "select * from details where userId = :userId and lastName = :lastName";
			Query query = session.createSQLQuery(sql);
			query.setParameter("userId", idz);
			query.setParameter("lastName", lname);

			query.setResultTransformer(Transformers.aliasToBean(Details.class));

			Details studResult = (Details) query.uniqueResult();

			//		System.out.println("Persons id: " + studResult.getUserId());
			//		System.out.println("Last Name: " + studResult.getLastName());
			//		System.out.println("First Name: " + studResult.getFirstName());
			//		System.out.println("Middle Name: " + studResult.getMiddleName());
			//		System.out.println("Course: " + studResult.getEdad());
			//		System.out.println();

			StringBuilder sb =new StringBuilder (studResult.getLastName());
			String rlname = "" + sb.reverse();
			sb =new StringBuilder (studResult.getFirstName());
			String rfname = "" + sb.reverse();
			sb =new StringBuilder (studResult.getMiddleName());
			String rmname = "" + sb.reverse();
			String number = String.valueOf(studResult.getEdad() );

			String S = "";
			for(int i = number.length() - 1; i >= 0; i--) {
				S = S + number.charAt(i);
			}
			int redad = Integer.valueOf(S);
			System.out.println("**********************");
			System.out.println();
			System.out.println("Persons id: " + studResult.getUserId());
			System.out.println("Last Name: " + rlname);
			System.out.println("First Name: " + rfname);
			System.out.println("Middle Name: " + rmname);
			System.out.println("Age: " + S);
			System.out.println();
			System.out.println("**********************");

		} catch (Exception ex) {
			System.out.println("NO EXISTING DATA FOUND");
			ex.printStackTrace();
		} finally {
			session.close();
		}		
		choyses();

	}
	
	private static void Insert() {
		int age = 0;
		Details dits = new Details();
		Session session = dbutil.getSession();
		Transaction tx = null;
		System.out.println(" *************");
		System.out.println("Enter the Following");
		System.out.println("lastname: ");
		String lastNameIns  = in.next();
		System.out.println("firstname: ");
		String firstNameIns  = in.next();
		System.out.println("middleName: ");
		String middleNameIns  = in.next();
		System.out.println("Age: ");
		int edadIns  = in.nextInt();
		System.out.println("");
		System.out.println(" *************");

		int Totalname = lastNameIns.length() + firstNameIns.length() + middleNameIns.length();
		int totalMIdLAst = lastNameIns.length() + middleNameIns.length();

		if (dits.fibo(edadIns) % 2 == 0) {
			// EVEN
			try {
				tx = session.beginTransaction();
				dits.setLastName(lastNameIns);
				dits.setFirstName(firstNameIns);
				dits.setMiddleName(middleNameIns);
				dits.AgeCheck(edadIns);
				dits.setEdad(edadIns);
				Integer id = (Integer) session.save(dits);
				
				int[] series = new int[edadIns];
				int sum = 0;
				int sum1 = 0;
				int sum2 = 0;

				// create first 2 series elements
				series[0] = 0;
				series[1] = 1;

				// create the Fibonacci series and store it in an array
				for (int i = 2; i < edadIns; i++) {
					series[i] = series[i - 1] + series[i - 2];
				}

				dits.check(series, totalMIdLAst, Totalname);
				tx.commit();
				System.out.println("Your Id " + id);
			} catch (Exception ex) {
				if (tx != null) {
					tx.rollback();
				}
				ex.printStackTrace();
			} finally {
				session.close();
			}
		} else {

			try {

				tx = session.beginTransaction();
				dits.setLastName(lastNameIns);
				dits.setFirstName(firstNameIns);
				dits.setMiddleName(middleNameIns);
				dits.AgeCheck(edadIns);
				dits.setEdad(edadIns);

				int[] series = new int[age];

				int sum = 0;
				int sum1 = 0;
				int sum2 = 0;

			
				series[0] = 0;
				series[1] = 1;

				for (int i = 2; i < age; i++) {
					series[i] = series[i - 1] + series[i - 2];
				}

				Integer id = (Integer) session.save(dits);
				dits.check(series, totalMIdLAst, Totalname);
				tx.commit();
				System.out.println("Your Id " + id);

			} catch (Exception ex) {

				if (tx != null) {
					tx.rollback();
				}
				ex.printStackTrace();
			} finally {
				session.close();
			}

		}
		choyses();

	}

}
