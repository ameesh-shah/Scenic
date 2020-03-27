
import json

from scenic.simulators.guideways.interface import Intersection, IntersectionWorkspace
from scenic.simulators.guideways.intersection import intersectionPath, intersectionOrigin
from scenic.simulators.webots.road.car_models import CarModel, carModels, modelWithName

# Load intersection information and set up workspace

if intersectionPath is None:
	raise RuntimeError('need to select intersection for this scenario')
with open(intersectionPath, 'r') as f:
	intersection = Intersection(json.load(f), origin=intersectionOrigin)

workspace = IntersectionWorkspace(intersection)

# Set up various regions

road = workspace.drivableRegion
roadDirection = workspace.roadDirection		# NOTE: chooses arbitrarily where guideways overlap!

# Types of objects

constructor Car:
	regionContainedIn: road
	position: Point on road
	heading: roadDirection at self.position
	webotsType: self.model.name
	model: modelWithName['ToyotaPrius']
	width: self.model.width
	height: self.model.height
	requireVisible: False
	viewAngle: 90 deg
	visibleDistance: 60
	cameraOffset: 0 @ (self.height / 2)		# camera is at the front

constructor Marker:
	width: 0.1
	height: 0.1
	allowCollisions: True
	requireVisible: False