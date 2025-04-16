# we can use this pattern when we have a lot of states and we want to change the state of the object

class ProjectState():
    state = None
  
    def set_state(self, state):
        self.state = state
        
    def get_state(self):
        return self.state
    
class ProjectStateYetToStart(ProjectState):
    def __init__(self):
        # here i need to perfomr some task before setting the state
        pass

    def get_state(self):
        return "yet_to_start"

    
class ProjectStateInProgress(ProjectState):
    
    def __init__(self):
        # here i need to perfomr some task before setting the state
        pass

    def get_state(self):
        return "in_progress"

class ProjectStateInQA(ProjectState): # we can extend this class to add more states 
    def __init__(self):
        # here i need to perfomr some task before setting the state
        pass

    def get_state(self):
        return "in_qa"

class ProjectStateCompleted(ProjectState):
    def __init__(self):
        # here i need to perfomr some task before setting the state
        pass

    def get_state(self):
        return "completed"
    
class Project:
    def __init__(self):
        self.project_state = ProjectState()
        self.project_state.set_state(ProjectStateYetToStart().get_state())
        
    def change_state(self, state):
        self.project_state.set_state(state.get_state())
    
    def get_project_state(self):
        return self.project_state.get_state()
    
def main():
    project = Project() # in state yet to start
    print(project.get_project_state())
    project.change_state(ProjectStateInProgress()) # in state in progress
    print(project.get_project_state())
    project.change_state(ProjectStateInQA()) # in state in QA
    print(project.get_project_state())
    project.change_state(ProjectStateCompleted()) # in state completed
    print(project.get_project_state())
    
if __name__ == "__main__":
    main()
    
        
        
    