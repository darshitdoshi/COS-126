class NoonSnooze
{
    public static void main(String args[])
    {
        int snooze = Integer.parseInt(args[0]);
        
        // snooze can be divided into hour, minute and slot 
        int snooze_hours = snooze/60;  //No. of hours
        String slot; // Slot can be am or pm
        int no_of_slots = snooze_hours/12;   //No. of 12 hour slots
        
        /*Even no of 12 hour slots from 12:00 pm would still be 12:00pm,
         Odd slots would be 12:00am */
        if(no_of_slots%2==0)
            slot = "pm"; 
        else
            slot = "am"; 
        
        /*After determining slot, we find the hour and minutes
         */
        int hours = (snooze_hours)%12; //Hour starts from 12 and loops around every 12 hours
        if(hours==0)
            hours=12;
        int mins = snooze%60;  //No. of minutes remaining after the last hour in snooze
        System.out.println(hours + ":" + mins + slot);
    }
    
}