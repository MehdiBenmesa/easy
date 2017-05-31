import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewStudentsComponent } from './new-students.component';

describe('NewStudentsComponent', () => {
  let component: NewStudentsComponent;
  let fixture: ComponentFixture<NewStudentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewStudentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewStudentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
