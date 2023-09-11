# My Personal Project

## A Fishing Ship Manager Application

The main function of this application is to keep track of the fish in your ship. For a little background information,
in my country, Indonesia, we have ships that specialize in catching mostly tunas (there are other types to for example,
swordfish). Each ship will go for pretty much as long as 6 months to catch fish. So, these ships have a storing 
capacity of around 60 tons. This application should be able to keep track of the information of the 
tunas that have been caught (species and weight). When the containers are already full the ships will be ordered to go
back.

If I could improve this project I will definitely ask my father to use it. This is also one of the reasons why 
this project interests me. My father works in this business and if he could benefit from this project it will be the 
best for me.

Types of fish that we catch:
- Yellowfin Tunas (sometimes also called ahi Tunas)
- Bluefin Tunas (really rare to get)
- Longfin Tunas (also called Albacore)
- Swordfish 
- etc.


**User Stories**:
- As a user, I want to be able to add a caught fish to my list of already caught fish.
- As a user, I want to be able to calculate the total weight of all the caught fish. 
- As a user, I want to be able to remove all the caught fish (scenarios where the ship has gone back and unloads everything).
- As a user, I want to be able to order the ship to go back when it's already at maximum capacity.
- As a user, I want to be able to know what are the fish that have been caught.

**User Stories Phase 2**:
- As a user, I want to be able to save my Ship with its information to file.
- As a user, I want to be able to load my Ship from a saved file.

##**Phase 4: Task 2**:
Thu Mar 31 23:02:38 PDT 2022\
Added a fish of species: Bluefin Tuna with weight(kg) of 60\
Thu Mar 31 23:02:48 PDT 2022\
Added a fish of species: Yellowfin Tuna with weight(kg) of 40\
Thu Mar 31 23:02:52 PDT 2022\
Added a fish of species: Yellowfin Tuna with weight(kg) of 25\
Thu Mar 31 23:02:59 PDT 2022\
Added a fish of species: Albacore with weight(kg) of 30\
Thu Mar 31 23:03:06 PDT 2022\
Removed all weight from ship!\
Thu Mar 31 23:03:06 PDT 2022\
Removed all fish from ship!

##**Phase 4: Task 3**:
I could make JPanel field inside of the AppFrame class to substitute the Title class, which would remove redundancy as 
the Title class is really simple. AppFrame and FishManager has the same arrows to JsonWriter, JsonReader, and Ship class,
there are possibly some duplicate codes, where can be made on an abstract class, where AppFrame and FishManager could
extend to.


