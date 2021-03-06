
/**
* Classical Change making problem with an unlimited amount of coins of each type. <br> 
* Version 1: Selection function with basic policy: First available coin.<br> 
* Leads to non-optimal solution.<br>
* The class encapsulates all the functions of the Greedy schema<br>
*/

public class ChangeMaking_1 {

	//---------------------------------------
	//	Constructor
	//---------------------------------------
	/**
	 * Constructor of the class. Do not edit it.
	 */
	public ChangeMaking_1(){}

	//-------------------------------------------------------------------
	// 0. displayElements --> Displays all elements of a MyList 
	//-------------------------------------------------------------------	
	/**
	 * Given a concrete MyList, this function displays its elements by screen (if any).
	 * @param m: The MyList we want to display its elements.	  
	 */	
	public void displayElements(MyList<Integer> m){
		//-----------------------------
		//SET OF OPS
		//-----------------------------

		//-----------------------------
		// I. SCENARIO IDENTIFICATION
		//-----------------------------
		int scenario = 0; 
		
		//Rule 1. MyList is empty
		if (m.length() == 0) 
			scenario = 1;
		//Rule 2. MyList is non-empty
		else
			scenario = 2;

		//-----------------------------
		// II. SCENARIO IMPLEMENTATION 
		//-----------------------------
		switch(scenario){	
				
		//Rule 1. MyList is empty
		case 1: 
			//1. We print the empty message
			System.out.println("Empty MyList");
			
			break;
			
		//Rule 2. MyList is non-empty
		case 2: 
			//1. We print the initial message
			int size = m.length();
			System.out.print("MyList has " + size + " items: [");
			
			//2. We traverse the coins
			for (int i = 0; i < size - 1; i++)
				System.out.print(m.getElement(i) + ", ");
				System.out.println(m.getElement(size - 1) + "]");
			
			break;
	
		}
		
	}
		
	//-------------------------------------------------------------------
	// 1. selectionFunction --> It selects the next candidate to be considered.  
	//-------------------------------------------------------------------	
	/**
	 * Given a current solution that is not a final solution, this function selects the new candidate to be added to it.<br> 
	 * The policy followed is very simple: Just pick the first non-discarded type of coin.
	 * @param changeGenerated: The quantity of change we have generated so far. 
	 * @param discarded: The MyList stating whether a candidate has been discarded so far or not.
	 * @param coinValues: A MyList containing the value of each type of coin supported. 
	 * @return: The index of candidate to be selected.
	 */	
	public int selectionFunction(int changeGenerated, 
								 MyList<Integer> discarded, 
								 MyList<Integer> coinValues)
	{
		
		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		int res = -1;

		//-----------------------------
		//SET OF OPS
		//-----------------------------

		//OP1. Auxiliary variables:
		//We use 'size' to compute just once the length of MyList 'items'.
		int size = coinValues.length();

		//We use 'index' to state the index of the candidate being checked.
		int index = 0;

		//OP1. We traverse all elements in coinValues, so as to find the first one not being picked so far. 
		while ((res == -1) && (index < size))
		{
			//OP1.1. If the coin has not been picked before, we pick it
			if (discarded.getElement(index) == 0)
			{
				res = index;
			}

			//OP1.2. We increase 'index' so as to try the next item
			index++;
		}

		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
		return res;	
	}
	
	
	//-------------------------------------------------------------------
	// 2. feasibilityTest --> It selects if a candidate can be added to the solution.  
	//-------------------------------------------------------------------	
	/**
	 * Given a current solution and a selected candidate, this function 
	 * states whether the candidate must be added to the solution or discarded.<br> 
	 * @param coinValues: A MyList containing the value of each type of coin supported. 
	 * @param amount: The amount of change we want to generate.
	 * @param changeGenerated: The quantity of change we have generated so far. 
	 * @param itemSelected: The index of the candidate selected.
	 * @return: Whether the candidate fits or not into the solution.
	 */	

	public boolean feasibilityTest(MyList<Integer> coinValues,
								   int amount,
								   int changeGenerated,
								   int itemSelected)
	{
		
		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		boolean res = false;

		//-----------------------------
		//SET OF OPS
		//-----------------------------

		//OP1. We check if the the coin can be used or not
		if (coinValues.getElement(itemSelected) + changeGenerated <= amount) 
		{
			res = true;
		}

		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
		return res;		
		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
	
	}

	
	//-------------------------------------------------------------------
	// 3. solutionTest --> It selects if the current solution is the final solution  
	//-------------------------------------------------------------------	
	/**
	 * Given a current solution, this function states whether it is a final solution or it can still be improved.<br> 
	 * To determine it, it checks whether there is (at least) one more coin that can be used as part of the change.
	 * @param changeGenerated: The change generated by the current solution. 
	 * @param discarded: The MyList stating whether a candidate has been discarded so far or not.
	 * @param coinValues: The MyList containing the set of coins supported. 
	 * @param amount: The amount of change we want to generate.
	 * @return: Whether the current solution is the final solution.
	 */	
	public boolean solutionTest(int changeGenerated,
								MyList<Integer> discarded,
								MyList<Integer> coinValues, 
							    int amount)
	{
		
		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		boolean res = true;

		//-----------------------------
		//SET OF OPS
		//-----------------------------
		//OP1. Auxiliary variables:
		//We use 'size' to compute just once the length of MyList 'items'.
		int size = coinValues.length();

		//We use 'index' to state the index of the candidate being checked.
		int index = 0;

		//OP1. We traverse all elements in coinValues, so as to check if one not being selected so far can be added to the changeGenerated. 
		while ((res == true) && (index < size))
		{
			//OP1.1. If the coin has not been picked before
			if (discarded.getElement(index) == 0)
			{
				//OP1.1.1. We check if we can use the coin to make change. 
				// If so, then the current solution can still be improved 
				if (coinValues.getElement(index) + changeGenerated <= amount) 
				{
					res = false;
				} 
				else 
				{
					discarded.addElement(index, 0);
				}
			}

			//OP1.2. We increase 'index' so as to try the next item
			index++;
		}

		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
		
		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
		return res;		
	}

	//-------------------------------------------------------------------
	// 4. objectiveFunction --> This function computes the value of the final solution.  
	//-------------------------------------------------------------------	
	/**
	 * Given the final solution to the problem, this function 
	 * computes its value according to:<br>
	 * How many coins are used in the solution.<br>
	 * How accurate it is the change.<br> 
	 * @param sol: The MyList containing the solution to the problem.
	 * @param changeGenerated: The change generated by the current solution. 
	 * @param amount: The amount of change we want to generate. 
	 * @return: The value of such solution.
	 */	
	public MyList<Integer> objectiveFunction(MyList<Integer> sol, 
											 int changeGenerated, 
											 int amount){
		
		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		MyList<Integer> res = new MyDynamicList<>();
		//OP1. Auxiliary variables:
		//We use 'size' to compute just once the length of MyList 'sol'.
		int size = sol.length() - 1;
		int totalCoins = 0;

		//OP1. We traverse all the items of the list
		while (size >= 0)
		{
			//OP1.1. We add +1 for each item taken in the solution
			res.addElement(0, sol.getElement(size));
			totalCoins = totalCoins + sol.getElement(size);
			//OP1.2. We decrease 'size' so as to try the previous item of 'sol'
			size--;

		}

		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------
		//OP2. We print out how many coins we used in the solution
		System.out.println("There were " + totalCoins + " coins used in solution");
		return res;

		//-----------------------------
		//SET OF OPS
		//-----------------------------
		
		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		

	}
	
	//-------------------------------------------------------------------
	// 5. solve --> This function solves the problem using a greedy algorithm.  
	//-------------------------------------------------------------------	
	/**
	 * Given an instance of the GP1 problem, this function solves it using 
	 * a greedy algorithm.<br> 
	 * @param coinValues: A MyList containing the value of each type of coin supported. 
	 * @param amount: The amount of change we want to generate.
	 * @return: A MyList containing the amount of coins of each type being selected.
	 */	
	public MyList<Integer> solve(MyList<Integer> coinValues, int amount)
	{
		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		MyList<Integer> res = null;
		MyList<Integer> solutionValue = null;
		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------

		//-----------------------------
		//SET OF OPS
		//-----------------------------

		//OP1. Auxiliary variables:
		//We use 'size' to compute just once the length of MyList 'items'.
		int size = coinValues.length();
	
		//We use 'discarded' as a MyList with the items that have been considered already.
		MyList<Integer> discarded = null;

		//We use 'changeGenerated' to state the amount of weight our bin contains so far.
		int changeGenerated = 0;			
		
		//OP1. We initialise all our variables: 
		
		//OP1.1. 'res' is initialised to be a list of the size of items, with all values being 0.
		res = new MyDynamicList<Integer>();
		for (int i = 0; i < size; i++)
			res.addElement(0, 0);

		//OP1.2. 'discarded' is initialised to be a list of the size of items, with all values being 0.
		discarded = new MyDynamicList<Integer>();
		for (int i = 0; i < size; i++)
			discarded.addElement(0, 0);
		
		//OP2. We construct the final solution:
		while (solutionTest(changeGenerated, discarded, coinValues, amount ) == false)
		{
			//OP2.1 Auxiliary variables:
			//We use 'itemSelected' to state the index of the candidate being selected.
			int itemSelected = -1;
			
			//OP2.1. We pick the most promising candidate
			itemSelected = selectionFunction(amount, discarded, coinValues);
					
			//OP2.2. If the candidate is feasible
			if (feasibilityTest(coinValues, amount, changeGenerated, itemSelected) == true)
			{
				//OP2.2.1. We add 1 to the solution and increment it, showing how many of each coin was used
				int numberOfCoins = res.getElement(itemSelected) + 1;
				res.removeElement(itemSelected);
				res.addElement(itemSelected,numberOfCoins);	
				//OP2.2.2. We update the amount of change generated
				changeGenerated = changeGenerated + coinValues.getElement(itemSelected);
			}
			else
			{
			//OP2.3. We discard the candidate for future iterations
				discarded.removeElement(itemSelected);
				discarded.addElement(itemSelected, 1);
			}
		}
	
		//OP3. We print the solution and its associated objective value
		solutionValue = objectiveFunction(res , changeGenerated, amount );
		displayElements(solutionValue);
		return res;		
	}
	
}