from sqlalchemy import Column, TEXT, INT, BIGINT
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class Quest(Base):
    __tablename__ = "quest"

    id = Column(BIGINT, nullable=False, autoincrement=True, primary_key=True)
    description = Column(TEXT, nullable=False)
    max_count = Column(INT, nullable=False)
    title = Column(TEXT, nullable=False)
